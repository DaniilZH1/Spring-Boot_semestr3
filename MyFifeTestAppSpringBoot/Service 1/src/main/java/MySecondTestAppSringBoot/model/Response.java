package MySecondTestAppSringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    /** Уникальный идентификатор входящего сообщения. */
    private String uid;

    /** Уникальный идентификатор операции. */
    private String operationUid;

    /** Время формирования ответа. */
    private String systemTime;

    /** Результат обработки запроса. */
    private Codes code;

    /** Код ошибки; при успешной обработке содержит пустое значение. */
    private ErrorCodes errorCode;

    /** Описание ошибки; при успешной обработке содержит пустое значение. */
    private ErrorMessages errorMessage;

    /** Рассчитанная годовая премия сотрудника. */
    private Double annualBonus;
}
