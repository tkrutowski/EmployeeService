package net.focik.hr.employee.domain.salary;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeCommandFacade;
import net.focik.hr.employee.domain.advance.AdvanceFacade;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceRepository;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryAdvanceRepositoryAdapter;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.money.Monetary;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalaryFacadeH2Test {

    static final Integer ID_EMPLOYEE = 22;
    static final LocalDate CALCULATE_SALARY_DATE = LocalDate.of(2021,3,1);
    @Autowired
    SalaryFacade salaryFacade;
    @Autowired
    EmployeeCommandFacade commandFacade;
    Employee employee;
    Salary salary;

    @BeforeEach
    void setUp() {
        employee = commandFacade.getEmployee(ID_EMPLOYEE);
        salary = salaryFacade.calculateSalary(employee, CALCULATE_SALARY_DATE);
    }
    @Test
    void should_return_4160_ragularRatey() {
        //given
        final BigDecimal EXPECTED = new BigDecimal("4160");

        //when
        BigDecimal result = employee.getRateRegularValueByDate(CALCULATE_SALARY_DATE);

        //then
        assertEquals(EXPECTED.doubleValue(), result.doubleValue());
    }

    @Test
    void should_return_26_overtimeRate() {
        //given
        final BigDecimal EXPECTED = new BigDecimal("26");

        //when
        BigDecimal result = employee.getRateOvertimeValueByDate(CALCULATE_SALARY_DATE);

        //then
        assertEquals(EXPECTED.doubleValue(), result.doubleValue());
    }

    @Test
    void should_return_PER_MONTH_rateType() {

        //when
        RateType result = employee.getRateRegularTypeByDate(CALCULATE_SALARY_DATE);

        //then
        assertEquals(RateType.PER_MONTH, result);
    }

    @Test
    void should_return_480_minutes_dayOff_pay() {
        //given
        final Integer EXPECTED = 480;

        //when
        Integer result = salary.getDayOffMinutesPay();

        //then
        assertEquals(EXPECTED, result);
    }

    @Test
    void should_return_0_minutes_dayOff_free() {
        //given
        final Integer EXPECTED = 0;

        //when
        Integer result = salary.getDayOffMinutesFree();

        //then
        assertEquals(EXPECTED, result);
    }

    @Test
    void should_return_3840_minutes_illness80() {
        //given
        final Integer EXPECTED = 3840;

        //when
        Integer result = salary.getIllnessMinutes80();

        //then
        assertEquals(EXPECTED, result);
    }

    @Test
    void should_return_0_minutes_illness100() {
        //given
        final Integer EXPECTED = 0;

        //when
        Integer result = salary.getIllnessMinutes100();

        //then
        assertEquals(EXPECTED, result);
    }

    @Test
    void should_return_7680_minutes_workRegular() {
        //given
        final Integer EXPECTED = 7680;

        //when
        Integer result = salary.getWorkRegularMinutes();

        //then
        assertEquals(EXPECTED, result);
    }

    @Test
    void should_return_1330_minutes_workOvertime50() {
        //given
        final Integer EXPECTED = 1330;

        //when
        Integer result = salary.getWorkOvertime50Minutes();

        //then
        assertEquals(EXPECTED, result);
    }

    @Test
    void should_return_0_minutes_workOvertime100() {
        //given
        final Integer EXPECTED = 0;

        //when
        Integer result = salary.getWorkOvertime100Minutes();

        //then
        assertEquals(EXPECTED, result);
    }

    @Test
    void should_return_220_10_workAll() {
        //given
        final String EXPECTED = "222:10";

        //when
        String result = salary.getWorkTimeAll();

        //then
        assertEquals(EXPECTED, result);
    }

    ///////////////////////////////
    //////////////////////////////  CALCULATE PAYMENT
    //////////////////////////////
    @Test
    void should_return_2893_91_for_rateRegular() {

        //when
        Money result = salary.getForRegularRate();

        //then
        assertEquals(Money.of(2893.91, "PLN"), result);
    }

    @Test
    void should_return_864_50_for_overtime50() {

        //when
        Money result = salary.getForOvertime50();

        //then
        assertEquals(Money.of(864.50, "PLN"), result);
    }

    @Test
    void should_return_0_for_overtime100() {

        //when
        Money result = salary.getForOvertime100();

        //then
        assertEquals(Money.of(0, "PLN"), result);
    }

    @Test
    void should_return_180_87_for_dayOff() {

        //when
        Money result = salary.getForDayOff();

        //then
        assertEquals(Money.of(180.87, "PLN").with(Monetary.getDefaultRounding()), result.with(Monetary.getDefaultRounding()));
    }

    @Test
    void should_return_1157_57_for_illness80() {
        //given
        Money expected = Money.of(1157.57, "PLN").with(Monetary.getDefaultRounding());
        //when
        Money result = salary.getForIllness80();

        //then
        assertEquals(expected, result.with(Monetary.getDefaultRounding()));
    }


    @Test
    void should_return_0_for_illness100() {

        //when
        Money result = salary.getForIllness100();

        //then
        assertEquals(Money.of(0, "PLN").with(Monetary.getDefaultRounding()), result.with(Monetary.getDefaultRounding()));
    }

    @Test
    void should_return_5096_84_for_all() {

        //when
        Money result = salary.getAmountForAllWorktime();

        //then
        assertEquals(Money.of(5096.84, "PLN").with(Monetary.getDefaultRounding()), result.with(Monetary.getDefaultRounding()));
    }

    @Test
    void should_return_1964_40_for_advances() {

        //when
        Money result = salary.getAdvancesSum();

        //then
        assertEquals(Money.of(1964.40, "PLN").with(Monetary.getDefaultRounding()), result.with(Monetary.getDefaultRounding()));
    }

    @Test
    void should_return_1000_for_loanistallemnt() {

        //when
        Money result = salary.getLoanInstallmentSum();

        //then
        assertEquals(Money.of(1000, "PLN").with(Monetary.getDefaultRounding()), result.with(Monetary.getDefaultRounding()));
    }

    @Test
    void should_return_1000_for_additions() {

        //when
        Money result = salary.getAdditionsSum();

        //then
        assertEquals(Money.of(1000, "PLN").with(Monetary.getDefaultRounding()), result.with(Monetary.getDefaultRounding()));
    }

    @Test
    void should_return_3132_44_for_toPay() {

        //when
        Money result = salary.getAmountToPay();

        //then
        assertEquals(Money.of(3132.44, "PLN").with(Monetary.getDefaultRounding()), result.with(Monetary.getDefaultRounding()));
    }
}