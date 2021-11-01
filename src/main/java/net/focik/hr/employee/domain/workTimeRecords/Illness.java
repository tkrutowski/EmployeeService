package net.focik.hr.employee.domain.workTimeRecords;

import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;

import java.time.LocalDate;
import java.time.LocalTime;

public class Illness extends WorkTime implements IWorkTime {
    private IllnessType illnessType;

    public Illness(int idEmployee, LocalDate date, IllnessType illnessType) {
        super(idEmployee, date);
        this.illnessType=illnessType;
    }

    @Override
    public LocalTime WorkTimeAll() {
        return LocalTime.of(8, 0);
    }

    @Override
    public LocalTime WorkTime50() {
        return LocalTime.of(0, 0);
    }

    @Override
    public LocalTime WorkTime100() {
        return LocalTime.of(0, 0);
    }
}
