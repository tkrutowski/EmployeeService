package net.focik.hr.employee.domain.salary;

import lombok.Builder;
import lombok.Getter;
import org.javamoney.moneta.Money;

@Getter
@Builder
public class Salary {
    private Money advancesSum;
    private Money additionsSum;
    private Money loanInstallmentSum;

    private Money forRegularRate;
    private Money forOvertime50;
    private Money forOvertime100;
    private Money forDayOff;
    private Money forIllness80;
    private Money forIllness100;

    private Integer dayOffMinutesPay;
    private Integer dayOffMinutesFree;
    private Integer illnessMinutes80;
    private Integer illnessMinutes100;
    private Integer workRegularMinutes;
    private Integer workOvertime50Minutes;
    private Integer workOvertime100Minutes;


    public String getWorkTimeAll() {
        int allMinutes = dayOffMinutesPay + dayOffMinutesFree + illnessMinutes80 + illnessMinutes100 +
                workRegularMinutes + workOvertime50Minutes + workOvertime100Minutes;
        return allMinutes / 60 +
                ":" +
                String.format("%02d", allMinutes % 60);
    }

    public Money getAmountForAllWorktime() {
        Money forAll = Money.of(0, "PLN");
        forAll = forAll.add(forRegularRate);
        forAll = forAll.add(forOvertime50);
        forAll = forAll.add(forOvertime100);
        forAll = forAll.add(forDayOff);
        forAll = forAll.add(forIllness80);
        forAll = forAll.add(forIllness100);

        return forAll;
    }

    public Money getAmountToPay() {
        Money toPay = getAmountForAllWorktime();
        toPay = toPay.subtract(advancesSum);
        toPay = toPay.subtract(loanInstallmentSum);
        toPay = toPay.add(additionsSum);

        return toPay;
    }

}
