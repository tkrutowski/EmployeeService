package net.focik.hr.employee.domain.salary;

import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.worktimerecords.IWorkTime;
import net.focik.hr.employee.domain.worktimerecords.Work;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalaryServiceOctoberTest {

    static SalaryService salaryService=new SalaryService();
    static List<IWorkTime> workTimeListOctober;
    static int  ID_EMPLOYEE=22;




    @BeforeAll
    static void beforeAll() {
        workTimeListOctober = new ArrayList<>();
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,1), LocalTime.of(7,0),LocalTime.of(15,20)));

        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,4), LocalTime.of(6,0),LocalTime.of(14,10)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,5), LocalTime.of(6,0),LocalTime.of(15,55)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,6), LocalTime.of(7,0),LocalTime.of(16,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,7), LocalTime.of(7,0),LocalTime.of(16,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,8), LocalTime.of(6,0),LocalTime.of(18,40)));

        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,11), LocalTime.of(7,0),LocalTime.of(15,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,12), LocalTime.of(7,0),LocalTime.of(15,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,13), LocalTime.of(7,0),LocalTime.of(17,45)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,14), LocalTime.of(7,0),LocalTime.of(15,15)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,15), LocalTime.of(6,0),LocalTime.of(15,45)));

        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,18), LocalTime.of(7,0),LocalTime.of(15,50)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,19), LocalTime.of(6,0),LocalTime.of(18,25)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,20), LocalTime.of(7,0),LocalTime.of(19,25)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,21), LocalTime.of(7,0),LocalTime.of(17,15)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,22), LocalTime.of(6,0),LocalTime.of(15,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,23), LocalTime.of(7,0),LocalTime.of(15,0)));

        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,25), LocalTime.of(7,0),LocalTime.of(18,15)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,26), LocalTime.of(7,0),LocalTime.of(15,30)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,27), LocalTime.of(7,0),LocalTime.of(15,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,28), LocalTime.of(7,0),LocalTime.of(15,50)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,10,29), LocalTime.of(7,0),LocalTime.of(15,50)));

        salaryService.calculateMinutes(workTimeListOctober);
    }

    @Test
    void should_return_0_dayOffMinutesPay_when_workTimeListOctober() {
        //given
        int EXPECTED = 0;

        //then
        assertEquals(EXPECTED, salaryService.getDayOffMinutesPay());
    }
    @Test
    void should_return_0_dayOffMinutesFree_when_workTimeListOctober() {
        //given
        int EXPECTED = 0;

        //then
        assertEquals(EXPECTED, salaryService.getDayOffMinutesFree());
    }

    @Test
    void should_return_0_illnesssMinutes80_when_workTimeListOctober_added() {
        //given
        int EXPECTED = 0;

        //then
        assertEquals(EXPECTED, salaryService.getIllnessMinutes80());
    }

    @Test
    void should_return_0_illnesssMinutes100_when_workTimeListOctober_added() {
        //given
        int EXPECTED = 0;

        //then
        assertEquals(EXPECTED, salaryService.getIllnessMinutes100());
    }

    @Test
    void should_return_10080_workRegularMinutes_when_workTimeListOctober_added() {
        //given
        int EXPECTED = 10080;

        //then
        assertEquals(EXPECTED, salaryService.getWorkRegularMinutes());
    }

    @Test
    void should_return_1930_workOver50Minutes_when_workTimeListOctober_added() {
        //given
        int EXPECTED = 1930;

        //then
        assertEquals(EXPECTED, salaryService.getWorkOvertime50Minutes());
    }

    @Test
    void should_return_480_workOver100Minutes_when_workTimeListOctober_added() {
        //given
        int EXPECTED = 480;

        //then
        assertEquals(EXPECTED, salaryService.getWorkOvertime100Minutes());
    }



    ///////////////////////////////
    //////////////////////////////  CALCULATE PAYMENT
    //////////////////////////////
    @Test
    void should_return_5440_when_calculateRegularWork_PER_MONTH_when_workTimeListOctober_added() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5440.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 168;

        //when
        Money result = salaryService.calculateForWorkRegular(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(5440, "PLN"), result);
    }

    @Test
    void should_return_1640_50_when_calculate_WorkOvertime50_when_workTimeListOctober_added() {
        //given
        BigDecimal RATE = new BigDecimal("34");

        //when
        Money result = salaryService.calculateForWorkOvertime50(RATE);

        //then
        assertEquals(Money.of(1640.50, "PLN"), result);
    }

    @Test
    void should_return_544_when_calculate_WorkOvertime100_when_workTimeListOctober_added() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(34.0d);

        //when
        Money result = salaryService.calculateForWorkOvertime100(RATE);

        //then
        assertEquals(Money.of(544, "PLN"), result);
    }

    @Test
    void should_return_0_when_calculateForDayOff_PER_MONTH_when_workTimeListOctober_added() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5440.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 168;

        //when
        Money result = salaryService.calculateForDayOff(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(0, "PLN"), result);
    }

    @Test
    void should_return_0_when_calculateForIllness_80_PER_MONTH_when_workTimeListOctober_added() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5440.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 168;

        //when
        Money result = salaryService.calculateForIllness80(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(0, "PLN"), result);
    }

    @Test
    void should_return_0_when_calculateForIllness_100_PER_MONTH_when_workTimeListOctober_added() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5440.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 168;

        //when
        Money result = salaryService.calculateForIllness100(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(0, "PLN"), result);
    }
}