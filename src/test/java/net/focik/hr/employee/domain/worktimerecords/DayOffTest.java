package net.focik.hr.employee.domain.worktimerecords;

import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DayOffTest {
    DayOff dayOff1, dayOff2, dayOff3, dayOff4,dayOff5,dayOff6,dayOff7;
    List<WorkTime> workTimeList;

    @BeforeEach
    void setUp() {
        workTimeList = new ArrayList<>();
        workTimeList.add(dayOff1 = new DayOff(1, LocalDate.of(2021,10,1), DayOffType.HALF_DAY));
        workTimeList.add(dayOff2 = new DayOff(1, LocalDate.of(2021,10,2), DayOffType.FREE));
        workTimeList.add(dayOff3 = new DayOff(1, LocalDate.of(2021,10,3),DayOffType.FREE));
        workTimeList.add(dayOff4 = new DayOff(1, LocalDate.of(2021,10,4),DayOffType.REST));
        workTimeList.add(dayOff5 = new DayOff(1, LocalDate.of(2021,10,5),DayOffType.OCCASIONAL));
        workTimeList.add(dayOff6 = new DayOff(1, LocalDate.of(2021,10,6),DayOffType.REST));
        workTimeList.add(dayOff7 = new DayOff(1, LocalDate.of(2021,10,7),DayOffType.FATHERLY));

    }

    @Test
    void should_return_4_hours_0_minutes_when_dayoff1() {
        //when
        LocalTime result = dayOff1.workTimeAll();

        //then
        assertEquals(LocalTime.of(4,0),result);
    }
    @Test
    void should_return_8_hours_0_minutes_when_dayoff2() {
        //when
        LocalTime result = dayOff2.workTimeAll();

        //then
        assertEquals(LocalTime.of(8,0),result);
    }
    @Test
    void should_return_8_hours_0_minutes_when_dayoff6() {
        //when
        LocalTime result = dayOff6.workTimeAll();

        //then
        assertEquals(LocalTime.of(8,0),result);
    }
    @Test
    void should_return_2160_minutes_when_workTimeList_added() {
        //given
        int numberOfMinutesAll = 0;

        //when
        for(WorkTime workTime : workTimeList)
        {
            if (workTime instanceof DayOff)//wpisywanie godzin pracy do grida
            {
                DayOff off = (DayOff)workTime;
                numberOfMinutesAll += off.totalMinutes(off.workTimeAll());
            }
        }

        //then
        assertEquals(3120,numberOfMinutesAll);
    }
}