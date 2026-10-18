package MySecondTestAppSringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor



public class Request {

    /** Должность сотрудника для определения коэффициента премии и статуса руководителя. */
    private Positions position;

    /** Оклад сотрудника для расчёта премии. */
    private Double salary;

    /** Коэффициент премии для годового и квартального расчёта. */
    private Double bonus;

    /** Количество дней, отработанных за год. */
    private Integer workDays;

    /** Уникальный идентификатор входящего сообщения. */
    @NotBlank
    @Size(max = 32)
    private String uid;

    /** Уникальный идентификатор операции. */
    @NotBlank
    @Size(max = 32)
    private String operationUid;

    /** Название системы, отправившей запрос. */
    private Systems systemName;

    /** Время формирования запроса в системе-отправителе. */
    @NotBlank
    private String systemTime;

    /** Источник запроса. */
    private String source;

    /** Идентификатор канала коммуникации. */
    @NotNull
    @Min(1)
    @Max(100000)
    private Integer communicationId;

    /** Идентификатор шаблона сообщения. */
    private Integer templateId;

    /** Код продукта, связанного с запросом. */
    private Integer productCode;

    /** Код SMS, связанного с запросом. */
    private Integer smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position=" + position +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}
