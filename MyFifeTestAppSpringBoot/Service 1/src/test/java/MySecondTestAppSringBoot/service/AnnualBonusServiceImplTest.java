package MySecondTestAppSringBoot.service;

import MySecondTestAppSringBoot.model.Positions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

class AnnualBonusServiceImplTest {


    @Test
    void testCalculate() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.0;

        double result = new AnnualBonusServiceImpl()
                .calculate(position, salary, bonus, workDays);

        double daysInYear = LocalDate.now().lengthOfYear();
        double expected = salary * bonus * daysInYear
                * position.getPositionCoefficient() / workDays;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testCalculateQuarterlyBonusForManager() {
        AnnualBonusServiceImpl service = new AnnualBonusServiceImpl();
        int workDays = 243;
        double salary = 100000.0;
        double bonus = 2.0;

        double result = service.calculateQuarterlyBonus(
                Positions.TL, salary, bonus, workDays
        );

        double expected = service.calculate(
                Positions.TL, salary, bonus, workDays
        ) / 4.0;
        assertEquals(expected, result, 0.001);
    }

    @Test
    void testCalculateQuarterlyBonusForNonManager() {
        double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(
                Positions.HR, 100000.0, 2.0, 243
        );

        assertEquals(0.0, result, 0.001);
    }
}
