package net.focik.hr.employee.domain.salary;

import lombok.Getter;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.workTimeRecords.DayOff;
import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;
import net.focik.hr.employee.domain.workTimeRecords.Illness;
import net.focik.hr.employee.domain.workTimeRecords.Work;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import javax.money.Monetary;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Service
class SalaryService {


    //    private List<IWorkTime> workTimeList;
    @Getter
    private int dayOffMinutesPay = 0;
    @Getter
    private int dayOffMinutesFree = 0;
    @Getter
    private int illnessMinutes80 = 0;
    @Getter private int illnessMinutes100 = 0;
    @Getter
    private int workRegularMinutes = 0;
    @Getter
    private int workOvertime50Minutes = 0;
    @Getter
    private int workOvertime100Minutes = 0;

    private final BigDecimal HOUR_IN_MINUTES = new BigDecimal("60");


    void calculateMinutes(List<IWorkTime> workTimeList) {
        resetValues();
        if (workTimeList != null) {
            for (IWorkTime workTime : workTimeList) {
                if (workTime instanceof DayOff) {
                    if(((DayOff) workTime).getDayOffType().getPercent() == 1)
                        dayOffMinutesPay += workTime.TotalMinutes(workTime.WorkTimeAll());
                    if(((DayOff) workTime).getDayOffType().getPercent() == 0)
                        dayOffMinutesFree += workTime.TotalMinutes(workTime.WorkTimeAll());
                } else if (workTime instanceof Illness) {
                    if(((Illness) workTime).getIllnessType().getPercent() == 0.8f)
                        illnessMinutes80 += workTime.TotalMinutes(workTime.WorkTimeAll());
                    if(((Illness) workTime).getIllnessType().getPercent() == 1.0f)
                        illnessMinutes100 += workTime.TotalMinutes(workTime.WorkTimeAll());
                } else if (workTime instanceof Work) {
                    workOvertime50Minutes += workTime.TotalMinutes(workTime.WorkTime50());
                    workOvertime100Minutes += workTime.TotalMinutes(workTime.WorkTime100());
                    workRegularMinutes += workTime.TotalMinutes(((Work) workTime).WorkTimeRegular());
                }
            }
        }
    }

    Money calculateForDayOff(BigDecimal rateRegularValueByDate, RateType rateRegularTypeByDate, int hoursToWork) {
        Money result = Money.of(BigDecimal.ZERO, "PLN");
        if (dayOffMinutesPay == 0)
            return result;

        switch (rateRegularTypeByDate) {
            case PER_HOUR:
                Money rateByHour = Money.of(rateRegularValueByDate, "PLN");
                result = rateByHour.multiply((getDivideByHOUR(dayOffMinutesPay)));
                break;
            case PER_MONTH:
                Money rateByMonth = Money.of(rateRegularValueByDate, "PLN").divide(hoursToWork);
                result = rateByMonth.multiply((getDivideByHOUR(dayOffMinutesPay)));
                break;
        }
        return result;
    }

    Money calculateForIllness80(BigDecimal rate, RateType rateType, int hoursToWork) {
        Money result = Money.of(BigDecimal.ZERO, "PLN");
        if (illnessMinutes80 == 0)
            return result;

        switch (rateType) {
            case PER_HOUR:
                Money rateByHour = Money.of(rate, "PLN").multiply(0.8d);
                result = rateByHour.multiply((getDivideByHOUR(illnessMinutes80)));
                break;
            case PER_MONTH:
                Money rateByMonth = (Money.of(rate, "PLN").divide(hoursToWork)).multiply(0.8d);
                result = rateByMonth.multiply((getDivideByHOUR(illnessMinutes80)));
                break;
        }
        return result;
    }



     Money calculateForIllness100(BigDecimal rate, RateType rateType, int hoursToWork) {
        Money result = Money.of(BigDecimal.ZERO, "PLN");
        if (illnessMinutes100 == 0)
            return result;

        switch (rateType) {
            case PER_HOUR:
                Money rateByHour = Money.of(rate, "PLN");
                result = rateByHour.multiply((getDivideByHOUR(illnessMinutes100)));
                break;
            case PER_MONTH:
                Money rateByMonth = Money.of(rate, "PLN").divide(hoursToWork);
                result = rateByMonth.multiply((getDivideByHOUR(illnessMinutes100)));
                break;
        }
        return result;
    }

    Money calculateForWorkRegular(BigDecimal rate, RateType rateType, int hoursToWork) {
        Money result = Money.of(0,"PLN");

        switch (rateType) {
            case PER_HOUR:
                Money rateByHour = Money.of(rate, "PLN");
                result = rateByHour.multiply((getDivideByHOUR(workRegularMinutes)));
                break;
            case PER_MONTH:
                Money rateByMonth = Money.of(rate, "PLN").divide(hoursToWork);
                result = rateByMonth.multiply((getDivideByHOUR(workRegularMinutes)));
                break;
        }
        return result.with(Monetary.getDefaultRounding());
    }

    Money calculateForWorkOvertime50(BigDecimal rate) {
        if(rate == null)
            return Money.of(0,"PLN");

        Money rateByHour = Money.of(rate, "PLN").multiply(1.5d);
        return rateByHour.multiply(getDivideByHOUR(workOvertime50Minutes)).with(Monetary.getDefaultRounding());
    }



    Money calculateForWorkOvertime100(BigDecimal rate) {
        if(rate == null)
            return Money.of(0,"PLN");

        Money rateByHour = Money.of(rate, "PLN").multiply(2d);
        return rateByHour.multiply(getDivideByHOUR(workOvertime100Minutes)).with(Monetary.getDefaultRounding());
    }

    private void resetValues() {
        dayOffMinutesPay = 0;
        dayOffMinutesFree = 0;
        illnessMinutes80 = 0;
        illnessMinutes100 = 0;
        workRegularMinutes = 0;
        workOvertime50Minutes = 0;
        workOvertime100Minutes = 0;
    }
    private BigDecimal getDivideByHOUR(int dividend) {
        MathContext mc = new MathContext(256, RoundingMode.HALF_EVEN);
        return new BigDecimal(dividend).divide(HOUR_IN_MINUTES, mc);
    }
}
