package net.focik.hr.employee.domain.worktimerecords;

import net.focik.hr.employee.domain.worktimerecords.share.IllnessType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IllnessTest {
    Illness illness1, illness2, illness3, illness4, illness5, illness6, illness7;
    List<WorkTime> workTimeList;

    @BeforeEach
    void setUp() {
        workTimeList = new ArrayList<>();
        workTimeList.add(illness1 = new Illness(1, LocalDate.of(2021, 10, 1), IllnessType.ILLNESS_80));
        workTimeList.add(illness2 = new Illness(1, LocalDate.of(2021, 10, 2), IllnessType.ILLNESS_100));
        workTimeList.add(illness3 = new Illness(1, LocalDate.of(2021, 10, 3), IllnessType.CARE));
        workTimeList.add(illness4 = new Illness(1, LocalDate.of(2021, 10, 4), IllnessType.DOWN_TIME));
        workTimeList.add(illness5 = new Illness(1, LocalDate.of(2021, 10, 5), IllnessType.INCIDENTIAL));
        workTimeList.add(illness6 = new Illness(1, LocalDate.of(2021, 10, 6), IllnessType.MOTHERLY));
        workTimeList.add(illness7 = new Illness(1, LocalDate.of(2021, 10, 7), IllnessType.ILLNESS_80));

    }


    @Test
    void should_return_8_hours_0_minutes_when_any_illness() {

        //then
        assertEquals(LocalTime.of(8, 0), illness1.workTimeAll());
        assertEquals(LocalTime.of(8, 0), illness2.workTimeAll());
        assertEquals(LocalTime.of(8, 0), illness3.workTimeAll());
        assertEquals(LocalTime.of(8, 0), illness4.workTimeAll());
        assertEquals(LocalTime.of(8, 0), illness5.workTimeAll());
        assertEquals(LocalTime.of(8, 0), illness6.workTimeAll());
        assertEquals(LocalTime.of(8, 0), illness7.workTimeAll());
    }

    @Test
    void should_return_3360_minutes_when_workTimeList_added() {
        //given
        int numberOfMinutesAll = 0;

        //when
        for (WorkTime workTime : workTimeList) {
            if (workTime instanceof Illness)//wpisywanie godzin pracy do grida
            {
                Illness illness = (Illness) workTime;
                numberOfMinutesAll += illness.totalMinutes(illness.workTimeAll());
            }
        }

        //then
        assertEquals(3360, numberOfMinutesAll);
    }

}