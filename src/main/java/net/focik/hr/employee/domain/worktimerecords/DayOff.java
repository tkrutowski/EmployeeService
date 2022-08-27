package net.focik.hr.employee.domain.worktimerecords;

import lombok.Getter;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;

import java.time.LocalDate;
import java.time.LocalTime;

public class DayOff extends WorkTime implements IWorkTime {
    @Getter DayOffType dayOffType;

    public DayOff(int idEmployee, LocalDate date, DayOffType dayOffType) {
        super(idEmployee, date);
        this.dayOffType = dayOffType;
    }

    @Override
    public LocalTime workTimeAll() {
        switch (dayOffType) {
            case HALF_DAY:
                 return LocalTime.of(4, 0);
            case FREE:
            case EDUCATIONAL:
                 return LocalTime.of(8, 0);
            default:
                return LocalTime.of(8, 0);
        }
    }

    @Override
    public LocalTime workTime50() {
        return LocalTime.of(0, 0);
    }

    @Override
    public LocalTime workTime100() {
        return LocalTime.of(0, 0);
    }
}
