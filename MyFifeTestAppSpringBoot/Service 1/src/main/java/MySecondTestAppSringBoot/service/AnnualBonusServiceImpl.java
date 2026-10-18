package MySecondTestAppSringBoot.service;


import MySecondTestAppSringBoot.model.Positions;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculateQuarterlyBonus(
            Positions position,
            double salary,
            double bonus,
            int workDays
    ) {
        if (!position.isManager()) {
            return 0.0;
        }

        return calculate(position, salary, bonus, workDays) / 4.0;
    }

    @Override
    public double calculate(
            Positions position,
            double salary,
            double bonus,
            int workDays
    ) {
        int daysInYear = LocalDate.now().lengthOfYear();
        return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
    }
}
