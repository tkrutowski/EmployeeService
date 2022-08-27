package net.focik.hr.employee.domain.worktimerecords;

import lombok.Getter;
import net.focik.hr.employee.domain.worktimerecords.share.IllnessType;

import java.time.LocalDate;
import java.time.LocalTime;

public class Illness extends WorkTime implements IWorkTime {
    @Getter
    private final IllnessType illnessType;

    public Illness(int idEmployee, LocalDate date, IllnessType illnessType) {
        super(idEmployee, date);
        this.illnessType = illnessType;
    }

    @Override
    public LocalTime workTimeAll() {
        return LocalTime.of(8, 0);
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
