package net.focik.hr.employee.domain.salary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.addition.AdditionFacade;
import net.focik.hr.employee.domain.advance.AdvanceFacade;
import net.focik.hr.employee.domain.loans.LoanFacade;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.worktimerecords.*;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
class SalaryFactory {
    private final AdvanceFacade advanceFacade;
    private final AdditionFacade additionFacade;
    private final LoanFacade loanFacade;
    private final WorkTimeFacade workTimeFacade;

    private final BigDecimal HOUR_IN_MINUTES = new BigDecimal("60");

    @Getter
    private int dayOffMinutesPay = 0;
    @Getter
    private int dayOffMinutesFree = 0;
    @Getter
    private int illnessMinutes80 = 0;
    @Getter
    private int illnessMinutes100 = 0;
    @Getter
    private int workRegularMinutes = 0;
    @Getter
    private int workOvertime50Minutes = 0;
    @Getter
    private int workOvertime100Minutes = 0;

    Salary createSalary(Employee employee, LocalDate date, DaysToWork daysToWork) {

        calculateMinutes(workTimeFacade.getAllByEmployeeIdAndDate(employee.getId(), date));

        return Salary.builder()
                .dayOffMinutesPay(getDayOffMinutesPay())
                .dayOffMinutesFree(getDayOffMinutesFree())
                .illnessMinutes80(getIllnessMinutes80())
                .illnessMinutes100(getIllnessMinutes100())
                .workRegularMinutes(getWorkRegularMinutes())
                .workOvertime50Minutes(getWorkOvertime50Minutes())
                .workOvertime100Minutes(getWorkOvertime100Minutes())

                .forRegularRate(calculateForWorkRegular(employee.getRateRegularByDate(date).getRateValue(), employee.getRateRegularByDate(date).getRateType(), daysToWork.getHoursToWork()))
                .forOvertime50(calculateForWorkOvertime50(employee.getRateOvertimeByDate(date).getRateValue()))
                .forOvertime100(calculateForWorkOvertime100(employee.getRateOvertimeByDate(date).getRateValue()))
                .forDayOff(calculateForDayOff(employee.getRateRegularByDate(date).getRateValue(), employee.getRateRegularByDate(date).getRateType(), daysToWork.getHoursToWork()))
                .forIllness80(calculateForIllness80(employee.getRateRegularByDate(date).getRateValue(), employee.getRateRegularByDate(date).getRateType(), daysToWork.getHoursToWork()))
                .forIllness100(calculateForIllness100(employee.getRateRegularByDate(date).getRateValue(), employee.getRateRegularByDate(date).getRateType(), daysToWork.getHoursToWork()))

                .daysToWork(daysToWork)
                .daysOffLeft(employee.getNumberDaysOffLeft())
                .loansToPay(loanFacade.getLoansToPaySum(employee.getId()))
                .advancesSum(advanceFacade.getAdvancesSumByIdEmployeeAndDate(employee.getId(), date))
                .additionsSum(additionFacade.getAdditionsSumByIdEmployeeAndDate(employee.getId(), date))
                .loanInstallmentSum(loanFacade.getInstallmentLoansSumByIdEmployeeAndDate(employee.getId(), date))
                .rateRegular(employee.getRateRegularByDate(date))
                .rateOvertime(employee.getRateOvertimeByDate(date))
                .build();
    }

    void calculateMinutes(List<IWorkTime> workTimeList) {
        resetValues();
        if (workTimeList != null) {
            for (IWorkTime workTime : workTimeList) {
                if (workTime instanceof DayOff) {
                    calculateMinutesDayOff(workTime);
                } else if (workTime instanceof Illness) {
                    calculateMinutesIllness(workTime);
                } else if (workTime instanceof Work) {
                    calculateMinutesWork(workTime);
                }
            }
        }
    }

    Money calculateForDayOff(BigDecimal rateRegularValueByDate, RateType rateType, int hoursToWork) {
        if (dayOffMinutesPay == 0)
            return getMoneyOfZero();

        if (rateType.equals(RateType.PER_HOUR)) {
            Money rateByHour = Money.of(rateRegularValueByDate, "PLN");
            return rateByHour.multiply((getDivideByHOUR(dayOffMinutesPay)));
        } else if (rateType.equals(RateType.PER_MONTH)) {
            Money rateByMonth = Money.of(rateRegularValueByDate, "PLN").divide(hoursToWork);
            return rateByMonth.multiply((getDivideByHOUR(dayOffMinutesPay)));
        }

        return getMoneyOfZero();
    }

    Money calculateForIllness80(BigDecimal rate, RateType rateType, int hoursToWork) {
        if (isZero(illnessMinutes80))
            return getMoneyOfZero();

        if (rateType.equals(RateType.PER_HOUR)) {
            Money rateByHour = Money.of(rate, "PLN").multiply(0.8d);
            return rateByHour.multiply((getDivideByHOUR(illnessMinutes80)));
        } else if (rateType.equals(RateType.PER_MONTH)) {
            Money rateByMonth = (Money.of(rate, "PLN").divide(hoursToWork)).multiply(0.8d);
            return rateByMonth.multiply((getDivideByHOUR(illnessMinutes80)));
        }

        return getMoneyOfZero();
    }

    Money calculateForIllness100(BigDecimal rate, RateType rateType, int hoursToWork) {
        if (isZero(illnessMinutes100))
            return getMoneyOfZero();

        if (rateType.equals(RateType.PER_HOUR)) {
            Money rateByHour = Money.of(rate, "PLN");
            return rateByHour.multiply((getDivideByHOUR(illnessMinutes100)));
        } else if (rateType.equals(RateType.PER_MONTH)) {
            Money rateByMonth = Money.of(rate, "PLN").divide(hoursToWork);
            return rateByMonth.multiply((getDivideByHOUR(illnessMinutes100)));
        }

        return getMoneyOfZero();
    }

    Money calculateForWorkRegular(BigDecimal rate, RateType rateType, int hoursToWork) {
        Money result = getMoneyOfZero();

        if (rateType.equals(RateType.PER_HOUR)) {
            Money rateByHour = Money.of(rate, "PLN");
            result = rateByHour.multiply((getDivideByHOUR(workRegularMinutes)));
        } else if (rateType.equals(RateType.PER_MONTH)) {
            Money rateByMonth = Money.of(rate, "PLN").divide(hoursToWork);
            result = rateByMonth.multiply((getDivideByHOUR(workRegularMinutes)));
        }

        return result.with(Monetary.getDefaultRounding());
    }

    Money calculateForWorkOvertime50(BigDecimal rate) {
        if (rate == null)
            return getMoneyOfZero();

        Money rateByHour = Money.of(rate, "PLN").multiply(1.5d);
        return rateByHour.multiply(getDivideByHOUR(workOvertime50Minutes)).with(Monetary.getDefaultRounding());
    }

    Money calculateForWorkOvertime100(BigDecimal rate) {
        if (rate == null)
            return Money.of(0, "PLN");

        Money rateByHour = Money.of(rate, "PLN").multiply(2d);
        return rateByHour.multiply(getDivideByHOUR(workOvertime100Minutes)).with(Monetary.getDefaultRounding());
    }

    private BigDecimal getDivideByHOUR(int dividend) {
        MathContext mc = new MathContext(256, RoundingMode.HALF_EVEN);
        return new BigDecimal(dividend).divide(HOUR_IN_MINUTES, mc);
    }

    private Money getMoneyOfZero() {
        return Money.of(BigDecimal.ZERO, "PLN");
    }

    private void calculateMinutesWork(IWorkTime workTime) {
        workOvertime50Minutes += workTime.totalMinutes(workTime.workTime50());
        workOvertime100Minutes += workTime.totalMinutes(workTime.workTime100());
        workRegularMinutes += workTime.totalMinutes(((Work) workTime).workTimeRegular());
    }

    private void calculateMinutesIllness(IWorkTime workTime) {
        if (((Illness) workTime).getIllnessType().getPercent() == 0.8f)
            illnessMinutes80 += workTime.totalMinutes(workTime.workTimeAll());
        if (((Illness) workTime).getIllnessType().getPercent() == 1.0f)
            illnessMinutes100 += workTime.totalMinutes(workTime.workTimeAll());
    }

    private void calculateMinutesDayOff(IWorkTime workTime) {
        if (((DayOff) workTime).getDayOffType().getPercent() == 1)
            dayOffMinutesPay += workTime.totalMinutes(workTime.workTimeAll());
        if (((DayOff) workTime).getDayOffType().getPercent() == 0)
            dayOffMinutesFree += workTime.totalMinutes(workTime.workTimeAll());
    }

    private boolean isZero(int param) {
        return param == 0;
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
}