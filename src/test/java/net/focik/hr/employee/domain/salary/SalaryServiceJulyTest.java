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

class SalaryServiceJulyTest {

    static SalaryService salaryService=new SalaryService();
    static List<IWorkTime> workTimeListOctober;
    static int  ID_EMPLOYEE=22;




    @BeforeAll
    static void beforeAll() {
        workTimeListOctober = new ArrayList<>();
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,1), LocalTime.of(7,0),LocalTime.of(18,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,2), LocalTime.of(7,0),LocalTime.of(15,0)));

        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,4), LocalTime.of(7,0),LocalTime.of(17,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,5), LocalTime.of(7,0),LocalTime.of(17,10)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,6), LocalTime.of(6,45),LocalTime.of(18,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,7), LocalTime.of(7,0),LocalTime.of(17,10)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,8), LocalTime.of(7,0),LocalTime.of(15,10)));

        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,11), LocalTime.of(7,0),LocalTime.of(18,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,12), LocalTime.of(6,0),LocalTime.of(17,30)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,13), LocalTime.of(6,0),LocalTime.of(17,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,14), LocalTime.of(6,0),LocalTime.of(17,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,15), LocalTime.of(6,0),LocalTime.of(16,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,16), LocalTime.of(7,0),LocalTime.of(14,0)));

        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,18), LocalTime.of(7,0),LocalTime.of(17,30)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,19), LocalTime.of(7,0),LocalTime.of(16,45)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,20), LocalTime.of(6,0),LocalTime.of(15,45)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,21), LocalTime.of(6,0),LocalTime.of(16,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,22), LocalTime.of(6,45),LocalTime.of(14,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,23), LocalTime.of(7,0),LocalTime.of(15,0)));

        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,25), LocalTime.of(7,0),LocalTime.of(16,40)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,26), LocalTime.of(6,0),LocalTime.of(18,45)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,27), LocalTime.of(6,0),LocalTime.of(15,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,28), LocalTime.of(6,0),LocalTime.of(15,0)));
        workTimeListOctober.add(new Work(ID_EMPLOYEE, LocalDate.of(2022,7,29), LocalTime.of(6,0),LocalTime.of(15,0)));

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
    void should_return_0_illnesssMinutes80_when_workTimeList_added() {
        //given
        int EXPECTED = 0;

        //then
        assertEquals(EXPECTED, salaryService.getIllnessMinutes80());
    }

    @Test
    void should_return_0_illnesssMinutes100_when_workTimeList_added() {
        //given
        int EXPECTED = 0;

        //then
        assertEquals(EXPECTED, salaryService.getIllnessMinutes100());
    }

    @Test
    void should_return_10035_workRegularMinutes_when_workTimeListOctober_added() {
        //given
        int EXPECTED = 10035;
        //then
        assertEquals(EXPECTED, salaryService.getWorkRegularMinutes());
    }

    @Test
    void should_return_3680_workOver50Minutes_when_workTimeList_added() {
        //given
        int EXPECTED = 2680;

        //then
        assertEquals(EXPECTED, salaryService.getWorkOvertime50Minutes());
    }

    @Test
    void should_return_1380_workOver100Minutes_when_workTimeList_added() {
        //given
        int EXPECTED = 1380;

        //then
        assertEquals(EXPECTED, salaryService.getWorkOvertime100Minutes());
    }



    ///////////////////////////////
    //////////////////////////////  CALCULATE PAYMENT
    //////////////////////////////
    @Test
    void should_return_5097_14_when_calculateRegularWork_PER_MONTH_when_workTimeList_added() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5120.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 168;

        //when
        Money result = salaryService.calculateForWorkRegular(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(5097.14, "PLN"), result);
    }

    @Test
    void should_return_2144_when_calculate_WorkOvertime50_when_workTimeList_added() {
        //given
        BigDecimal RATE = new BigDecimal("32");

        //when
        Money result = salaryService.calculateForWorkOvertime50(RATE);

        //then
        assertEquals(Money.of(2144, "PLN"), result);
    }

    @Test
    void should_return_1472_when_calculate_WorkOvertime100_when_workTimeList_added() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(32.0d);

        //when
        Money result = salaryService.calculateForWorkOvertime100(RATE);

        //then
        assertEquals(Money.of(1472, "PLN"), result);
    }

    @Test
    void should_return_0_when_calculateForDayOff_PER_MONTH_when_workTimeListOctober_added() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5120.0d);
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
        BigDecimal RATE = BigDecimal.valueOf(5120.0d);
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
        BigDecimal RATE = BigDecimal.valueOf(5120.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 168;

        //when
        Money result = salaryService.calculateForIllness100(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(0, "PLN"), result);
    }
}