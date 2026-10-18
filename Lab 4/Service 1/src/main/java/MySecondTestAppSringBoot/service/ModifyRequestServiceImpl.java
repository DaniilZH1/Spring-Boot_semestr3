package MySecondTestAppSringBoot.service;

import MySecondTestAppSringBoot.model.Request;
import MySecondTestAppSringBoot.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Slf4j
@Service
public class ModifyRequestServiceImpl implements ModifyRequestService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void modify(Request request, long requestStartTime) {
        request.setSource("Service 1");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Request-Start-Time", String.valueOf(requestStartTime));

        log.info("Отправляем запрос из Сервиса 1 в Сервис 2: {}", request);

        HttpEntity<Request> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<Response> response = restTemplate.exchange(
                "http://localhost:8084/feedback",
                HttpMethod.POST,
                requestEntity,
                Response.class
        );

        log.info("Получен ответ от Сервиса 2: {}", response.getBody());
    }
}
