package net.focik.hr.employee.domain.salary;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryTest {

    Salary salary;
    @BeforeEach
    void setUp() {
        salary = Salary.builder()
                .forDayOff(Money.of(0,"PLN"))
                .forIllness80(Money.of(0,"PLN"))
                .forIllness100(Money.of(0,"PLN"))
                .forRegularRate(Money.of(5440,"PLN"))
                .forOvertime50(Money.of(1640.50,"PLN"))
                .forOvertime100(Money.of(544,"PLN"))
                .loanInstallmentSum(Money.of(0,"PLN"))
                .additionsSum(Money.of(874,"PLN"))
                .advancesSum(Money.of(2048.67,"PLN"))
                .dayOffMinutesPay(0)
                .dayOffMinutesFree(0)
                .illnessMinutes80(0)
                .illnessMinutes100(0)
                .workRegularMinutes(10080)
                .workOvertime50Minutes(1930)
                .workOvertime100Minutes(480)
                .build();
    }

    @Test
    void getNumberOfHours() {
        //given
        String EXPECTED = "208:10";

        //then
        assertEquals(EXPECTED, salary.getWorkTimeAll());
    }

    @Test
    void getAmountForAllWorktime() {
        //given
        Money EXPECTED = Money.of(7624.50,"PLN");

        //then
        assertEquals(EXPECTED, salary.getAmountForAllWorktime());
    }

    @Test
    void amountToPay() {
        //given
        Money EXPECTED = Money.of(6449.83,"PLN");

        //then
        assertEquals(EXPECTED, salary.getAmountToPay());
    }
}