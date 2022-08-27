package net.focik.hr.employee.domain.salary;

import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.worktimerecords.DayOff;
import net.focik.hr.employee.domain.worktimerecords.IWorkTime;
import net.focik.hr.employee.domain.worktimerecords.Illness;
import net.focik.hr.employee.domain.worktimerecords.Work;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;
import net.focik.hr.employee.domain.worktimerecords.share.IllnessType;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalaryServiceTest {

    SalaryService salaryService=new SalaryService();
    static List<IWorkTime> workTimeListNovember;
    static List<IWorkTime> workTimeListOctober;
    static int  ID_EMPLOYEE=22;

    @BeforeAll
    static void beforeAll() {

        workTimeListNovember = new ArrayList<>();
        workTimeListNovember.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,1), LocalTime.of(7,0),LocalTime.of(16,15)));
        workTimeListNovember.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,3), LocalTime.of(7,0),LocalTime.of(17,58)));
        workTimeListNovember.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,4), LocalTime.of(7,0),LocalTime.of(15,36)));

        workTimeListNovember.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,5), DayOffType.REST));
        workTimeListNovember.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,6), DayOffType.HALF_DAY));

        workTimeListNovember.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,6), LocalTime.of(7,0),LocalTime.of(16,0)));

        workTimeListNovember.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,9), IllnessType.ILLNESS_80));
        workTimeListNovember.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,10), IllnessType.ILLNESS_80));
        workTimeListNovember.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,11), IllnessType.ILLNESS_80));
        workTimeListNovember.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,12), IllnessType.ILLNESS_100));
        workTimeListNovember.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,13), IllnessType.ILLNESS_100));
    }

    @Test
    void should_return_720_dayOffMinutesPay() {
        //given
        int EXPECTED = 720;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,5), DayOffType.REST));
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,6), DayOffType.HALF_DAY));

        //when
        salaryService.calculateMinutes(list);

        //then
        assertEquals(EXPECTED, salaryService.getDayOffMinutesPay());
    }

    @Test
    void should_return_960_dayOffMinutesFree() {
        //given
        int EXPECTED = 960;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,5), DayOffType.FREE));
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,6), DayOffType.EDUCATIONAL));
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,6), DayOffType.FATHERLY));

        //when
        salaryService.calculateMinutes(list);

        //then
        assertEquals(EXPECTED, salaryService.getDayOffMinutesFree());
    }

    @Test
    void should_return_1440_illnesssMinutes80_when_workTimeList_added() {
        //given
        int EXPECTED = 1440;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,9), IllnessType.ILLNESS_80));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,10), IllnessType.ILLNESS_80));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,11), IllnessType.ILLNESS_80));

        //when
        salaryService.calculateMinutes(list);

        //then
        assertEquals(EXPECTED, salaryService.getIllnessMinutes80());
    }

    @Test
    void should_return_960_illnesssMinutes100_when_workTimeList_added() {
        //given
        int EXPECTED = 960;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,12), IllnessType.ILLNESS_100));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,13), IllnessType.ILLNESS_100));

        //when
        salaryService.calculateMinutes(list);

        //then
        assertEquals(EXPECTED, salaryService.getIllnessMinutes100());
    }

    @Test
    void should_return_289_workOver50Minutes_when_workTimeList_added() {
        //given
        int EXPECTED = 289;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,2), LocalTime.of(7,0),LocalTime.of(16,15)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,3), LocalTime.of(7,0),LocalTime.of(17,58)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,4), LocalTime.of(7,0),LocalTime.of(15,36)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,6), LocalTime.of(7,0),LocalTime.of(16,0)));


        //when
        salaryService.calculateMinutes(list);

        //then
        assertEquals(EXPECTED, salaryService.getWorkOvertime50Minutes());
    }
    @Test
    void should_return_540_workOver100Minutes_when_workTimeList_added() {
        //given
        int EXPECTED = 540;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,2), LocalTime.of(7,0),LocalTime.of(16,15)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,3), LocalTime.of(7,0),LocalTime.of(17,58)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,4), LocalTime.of(7,0),LocalTime.of(15,36)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,6), LocalTime.of(7,0),LocalTime.of(16,0)));

        //when
        salaryService.calculateMinutes(list);

        //then
        assertEquals(EXPECTED, salaryService.getWorkOvertime100Minutes());
    }
    @Test
    void should_return_1440_workRegularMinutes_when_workTimeList_added() {
        //given
        int EXPECTED = 1440;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,2), LocalTime.of(7,0),LocalTime.of(16,15)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,3), LocalTime.of(7,0),LocalTime.of(17,58)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,4), LocalTime.of(7,0),LocalTime.of(15,36)));
        list.add(new Work(ID_EMPLOYEE, LocalDate.of(2021,11,6), LocalTime.of(7,0),LocalTime.of(16,0)));

        //when
        salaryService.calculateMinutes(list);

        //then
        assertEquals(EXPECTED, salaryService.getWorkRegularMinutes());
    }
    @Test
    void calculateForDayOff_PER_MONTH() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5440.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 160;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,5), DayOffType.REST));
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,6), DayOffType.HALF_DAY));

        //when
        salaryService.calculateMinutes(list);
        Money result = salaryService.calculateForDayOff(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(408, "PLN"), result);
    }

    @Test
    void calculateForDayOff_PER_HOUR() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(21.30d);
        RateType RATE_TYPE = RateType.PER_HOUR;
        int HOURS_TO_WORK = 160;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,5), DayOffType.REST));
        list.add(new DayOff(ID_EMPLOYEE, LocalDate.of(2021,11,6), DayOffType.HALF_DAY));

        //when
        salaryService.calculateMinutes(list);
        Money result = salaryService.calculateForDayOff(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(255.60, "PLN"), result);
    }

    @Test
    void calculateForIllness_80_PER_HOUR() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(21.30d);
        RateType RATE_TYPE = RateType.PER_HOUR;
        int HOURS_TO_WORK = 160;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,9), IllnessType.ILLNESS_80));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,10), IllnessType.ILLNESS_80));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,11), IllnessType.ILLNESS_80));

        //when
        salaryService.calculateMinutes(list);
        Money result = salaryService.calculateForIllness80(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(408.96, "PLN"), result);
    }

    @Test
    void calculateForIllness_80_PER_MONTH() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5440.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 160;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,9), IllnessType.ILLNESS_80));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,10), IllnessType.ILLNESS_80));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,11), IllnessType.ILLNESS_80));

        //when
        salaryService.calculateMinutes(list);
        Money result = salaryService.calculateForIllness80(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(652.80, "PLN"), result);
    }

    @Test
    void calculateForIllness_100_PER_HOUR() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(21.30d);
        RateType RATE_TYPE = RateType.PER_HOUR;
        int HOURS_TO_WORK = 160;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,12), IllnessType.ILLNESS_100));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,13), IllnessType.ILLNESS_100));

        //when
        salaryService.calculateMinutes(list);
        Money result = salaryService.calculateForIllness100(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(340.80, "PLN"), result);
    }

    @Test
    void calculateForIllness_100_PER_MONTH() {
        //given
        BigDecimal RATE = BigDecimal.valueOf(5440.0d);
        RateType RATE_TYPE = RateType.PER_MONTH;
        int HOURS_TO_WORK = 160;
        List<IWorkTime> list = new ArrayList<>();
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,12), IllnessType.ILLNESS_100));
        list.add(new Illness(ID_EMPLOYEE, LocalDate.of(2021,11,13), IllnessType.ILLNESS_100));

        //when
        salaryService.calculateMinutes(list);
        Money result = salaryService.calculateForIllness100(RATE, RATE_TYPE, HOURS_TO_WORK);

        //then
        assertEquals(Money.of(544, "PLN"), result);
    }


}