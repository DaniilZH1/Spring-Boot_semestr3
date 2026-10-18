package MySecondTestAppSringBoot.controller;

import MySecondTestAppSringBoot.exception.UnsupportedCodeException;
import MySecondTestAppSpringBoot.exception.ValidationFailedException;
import MySecondTestAppSringBoot.model.Codes;
import MySecondTestAppSringBoot.model.ErrorCodes;
import MySecondTestAppSringBoot.model.ErrorMessages;
import MySecondTestAppSringBoot.model.Request;
import MySecondTestAppSringBoot.model.Response;
import MySecondTestAppSringBoot.service.AnnualBonusService;
import MySecondTestAppSringBoot.service.ModifyRequestService;
import MySecondTestAppSringBoot.service.ModifyResponseService;
import MySecondTestAppSringBoot.service.ValidationService;
import MySecondTestAppSringBoot.util.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyOperationUidResponseService;
    private final ModifyResponseService modifySystemTimeResponseService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;

    public MyController(
            ValidationService validationService,
            @Qualifier("ModifyOperationUidResponseService")
            ModifyResponseService modifyOperationUidResponseService,
            @Qualifier("ModifySystemTimeResponseService")
            ModifyResponseService modifySystemTimeResponseService,
            ModifyRequestService modifyRequestService,
            AnnualBonusService annualBonusService
    ) {
        this.validationService = validationService;
        this.modifyOperationUidResponseService = modifyOperationUidResponseService;
        this.modifySystemTimeResponseService = modifySystemTimeResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<Response> feedback(
            @Valid @RequestBody Request request,
            BindingResult bindingResult
    ) {
        long requestStartTime = System.currentTimeMillis();
        log.info("Получен запрос: {}", request);

        Response response = createInitialResponse(request);

        try {
            validationService.isValid(bindingResult, request.getUid());
            response.setAnnualBonus(annualBonusService.calculate(
                    request.getPosition(),
                    request.getSalary(),
                    request.getBonus(),
                    request.getWorkDays()
            ));

            response = modifyOperationUidResponseService.modify(response);
            response = modifySystemTimeResponseService.modify(response);
            modifyRequestService.modify(request, requestStartTime);

            log.info("Сформирован ответ: {}", response);
            return ResponseEntity.ok(response);
        } catch (ValidationFailedException e) {
            log.error("Ошибка валидации: {}", e.getMessage());
            return createErrorResponse(
                    response,
                    ErrorCodes.VALIDATION_EXCEPTION,
                    ErrorMessages.VALIDATION,
                    HttpStatus.BAD_REQUEST
            );
        } catch (UnsupportedCodeException e) {
            log.error("Неподдерживаемый код: {}", e.getMessage());
            return createErrorResponse(
                    response,
                    ErrorCodes.UNSUPPORTED_EXCEPTION,
                    ErrorMessages.UNSUPPORTED,
                    HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            log.error("Непредвиденная ошибка при обработке запроса", e);
            return createErrorResponse(
                    response,
                    ErrorCodes.UNKNOWN_EXCEPTION,
                    ErrorMessages.UNKNOWN,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    private Response createInitialResponse(Request request) {
        Response response = new Response();
        response.setUid(request.getUid());
        response.setOperationUid(request.getOperationUid());
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        response.setCode(Codes.SUCCESS);
        response.setErrorCode(ErrorCodes.EMPTY);
        response.setErrorMessage(ErrorMessages.EMPTY);
        return response;
    }

    private ResponseEntity<Response> createErrorResponse(
            Response response,
            ErrorCodes errorCode,
            ErrorMessages errorMessage,
            HttpStatus status
    ) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        log.info("Сформирован ответ: {}", response);
        return new ResponseEntity<>(response, status);
    }
}
