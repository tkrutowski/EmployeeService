package net.focik.hr.employee.domain.worktimerecords;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkTest {

    Work work1, work2, work3, work4, work5, work6, work7, work8, work9, work10;
    Work work11, work12, work13, work14, work15, work16, work17, work18, work19, work20;
    Work work21, work22, work23, work24, work25, work26, work27, work28, work29, work30, work31;
    Work workSaturday;
    List<WorkTime> workTimeList;
    @BeforeEach
    void setUp() {
        workTimeList = new ArrayList<>();
        workTimeList.add(work1 = new Work(1, LocalDate.of(2021,10,1), LocalTime.of(7,0),LocalTime.of(15,20)));

        workTimeList.add(work4 = new Work(1, LocalDate.of(2021,10,4), LocalTime.of(6,0),LocalTime.of(14,10)));
        workTimeList.add(work5 = new Work(1, LocalDate.of(2021,10,5), LocalTime.of(6,0),LocalTime.of(15,55)));
        workTimeList.add(work6 = new Work(1, LocalDate.of(2021,10,6), LocalTime.of(7,0),LocalTime.of(16,0)));
        workTimeList.add(work7 = new Work(1, LocalDate.of(2021,10,7), LocalTime.of(7,0),LocalTime.of(16,0)));
        workTimeList.add(work8 = new Work(1, LocalDate.of(2021,10,8), LocalTime.of(6,0),LocalTime.of(18,40)));

        workTimeList.add(work11 = new Work(1, LocalDate.of(2021,10,11), LocalTime.of(7,0),LocalTime.of(15,0)));
        workTimeList.add(work12 = new Work(1, LocalDate.of(2021,10,12), LocalTime.of(7,0),LocalTime.of(15,0)));
        workTimeList.add(work13 = new Work(1, LocalDate.of(2021,10,13), LocalTime.of(7,0),LocalTime.of(17,45)));
        workTimeList.add(work14 = new Work(1, LocalDate.of(2021,10,14), LocalTime.of(7,0),LocalTime.of(15,15)));
        workTimeList.add(work15 = new Work(1, LocalDate.of(2021,10,15), LocalTime.of(6,0),LocalTime.of(15,45)));

        workTimeList.add(work18 = new Work(1, LocalDate.of(2021,10,18), LocalTime.of(7,0),LocalTime.of(15,50)));
        workTimeList.add(work19 = new Work(1, LocalDate.of(2021,10,19), LocalTime.of(6,0),LocalTime.of(18,25)));
        workTimeList.add(work20 = new Work(1, LocalDate.of(2021,10,20), LocalTime.of(7,0),LocalTime.of(19,25)));
        workTimeList.add(work21 = new Work(1, LocalDate.of(2021,10,21), LocalTime.of(7,0),LocalTime.of(17,15)));
        workTimeList.add(work22 = new Work(1, LocalDate.of(2021,10,22), LocalTime.of(6,0),LocalTime.of(15,0)));
        workTimeList.add(work23 = new Work(1, LocalDate.of(2021,10,23), LocalTime.of(7,0),LocalTime.of(15,0)));

        workTimeList.add(work25 = new Work(1, LocalDate.of(2021,10,25), LocalTime.of(7,0),LocalTime.of(18,15)));
        workTimeList.add(work26 = new Work(1, LocalDate.of(2021,10,26), LocalTime.of(7,0),LocalTime.of(15,30)));
        workTimeList.add(work27 = new Work(1, LocalDate.of(2021,10,27), LocalTime.of(7,0),LocalTime.of(15,0)));
        workTimeList.add(work28 = new Work(1, LocalDate.of(2021,10,28), LocalTime.of(7,0),LocalTime.of(15,50)));
        workTimeList.add(work29 = new Work(1, LocalDate.of(2021,10,29), LocalTime.of(7,0),LocalTime.of(15,50)));
        workTimeList.add(work30 = new Work(1, LocalDate.of(2021,10,30), LocalTime.of(7,0),LocalTime.of(16,0)));
//        workSaturday = new Work(1, LocalDate.of(2021,10,16), LocalTime.of(7,0),LocalTime.of(16,20));
    }

    @Test
    void should_return_8_hours_0_minutes_when_work1() {
        //when
        LocalTime result = work11.workTimeAll();

        //then
        assertEquals(LocalTime.of(8,0),result);
    }

    @Test
    void should_return_13030_minutes_when_workTimeList_added() {
        //given
        int numberOfMinutesAll = 0;

        //when
        for(WorkTime workTime : workTimeList)
        {
            if (workTime instanceof Work)//wpisywanie godzin pracy do grida
            {
                Work w = (Work)workTime;
                numberOfMinutesAll += w.totalMinutes(w.workTimeAll());
            }
        }

        //then
        assertEquals(13030,numberOfMinutesAll);
    }

    @Test
    void should_return_1930_minutes_when_workTimeList_added() {
        //given
        int numberOfMinutes50 = 0;

        //when
        for(WorkTime workTime : workTimeList)
        {
            if (workTime instanceof Work)//wpisywanie godzin pracy do grida
            {
                Work w = (Work)workTime;
                numberOfMinutes50 += w.totalMinutes(w.workTime50());
            }
        }

        //then
        assertEquals(1930,numberOfMinutes50);
    }

    @Test
    void should_return_1020_minutes_100_when_workTimeList_added() {
        //given
        int numberOfMinutes100 = 0;
        //when
        for(WorkTime workTime : workTimeList)
        {
            if (workTime instanceof Work)//wpisywanie godzin pracy do grida
            {
                Work w = (Work)workTime;
                numberOfMinutes100 += w.totalMinutes(w.workTime100());
            }
        }

        //then
        assertEquals(1020,numberOfMinutes100);
    }

    @Test
    void should_return_0_hours_0_minutes_when_work1() {
        //when
        LocalTime result = work11.workTime50();

        //then
        assertEquals(LocalTime.of(0,0),result);
    }

    @Test
    void should_return_2_hours_15_minutes_when_work21() {
        //when
        LocalTime result = work21.workTime50();

        //then
        assertEquals(LocalTime.of(2,15),result);
    }

    @Test
    void workTime100() {
    }
}