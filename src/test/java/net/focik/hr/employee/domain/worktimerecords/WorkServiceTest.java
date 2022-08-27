package net.focik.hr.employee.domain.worktimerecords;

import net.focik.hr.employee.domain.exceptions.WorkTimeNotValidException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class WorkServiceTest {
    WorkService workService = new WorkService(null);

    @Test
    void should_throw_NullPointerException_when_work_is_null() {
        //given
        Work w1 = null;

        //when
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                workService.addWorkTime(w1));
        assertEquals("IWorkTime can't be null.", exception.getMessage());
    }

    @Test
    void should_throw_NullPointerException_when_starttime_is_null() {
        //given
        Work w1 = new Work(1, LocalDate.of(2000,12,12), null, null);

        //when
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                workService.addWorkTime(w1));
        assertEquals("StartTime can't be null.", exception.getMessage());
    }

    @Test
    void should_throw_NullPointerException_when_stoptime_is_null() {
        //given
        Work w1 = new Work(1, LocalDate.of(2000,12,12), null, null);

        //when
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                workService.addWorkTime(w1));
        assertEquals("StartTime can't be null.", exception.getMessage());
    }

    @Test
    void should_throw_WorkTimeNotValidException_when_starttime_is_before_stoptime() {
        //given
        Work w1 = new Work(1, LocalDate.of(2000,12,12), LocalTime.of(8,0), LocalTime.of(7,0));

        //when
        WorkTimeNotValidException exception = assertThrows(WorkTimeNotValidException.class, () ->
                workService.addWorkTime(w1));
        assertEquals("Czas zakończenia nie może być wcześniejszy niż czas rozpoczęcia.", exception.getMessage());
    }
}