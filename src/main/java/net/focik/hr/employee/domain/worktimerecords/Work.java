package net.focik.hr.employee.domain.worktimerecords;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class Work extends WorkTime implements IWorkTime {

    private LocalTime stopTime;
    private LocalTime startTime;

    public Work(int idEmployee, LocalDate date, LocalTime startTime, LocalTime stopTime) {
        super(idEmployee, date);
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    @Override
    public LocalTime workTimeAll() {
        return stopTime.minusMinutes(startTime.getHour() * 60L + startTime.getMinute());
    }

    @Override
    public LocalTime workTime50() {
        if (getDate().getDayOfWeek() != DayOfWeek.SATURDAY && getDate().getDayOfWeek() != DayOfWeek.SUNDAY) {
            if (workTimeAll().getHour() >= 8)
                return workTimeAll().minusHours(8);
            else//jeżeli poniżej 8 godzin (żeby nie wyświetlało godzin ujemnych
                return LocalTime.of(0, 0, 0);
        } else
            return LocalTime.of(0, 0, 0);
    }

    @Override
    public LocalTime workTime100() {
        if (getDate().getDayOfWeek() == DayOfWeek.SATURDAY || getDate().getDayOfWeek() == DayOfWeek.SUNDAY)
            return stopTime.minusMinutes(startTime.getHour() * 60L + startTime.getMinute());

        return LocalTime.of(0, 0);
    }

    public LocalTime workTimeRegular() {
        if ((getDate().getDayOfWeek() != DayOfWeek.SATURDAY && getDate().getDayOfWeek() != DayOfWeek.SUNDAY) && (stopTime.minusMinutes(startTime.getHour() * 60L + startTime.getMinute()).getHour() >= 8))
            return LocalTime.of(8, 0);
        else if ((getDate().getDayOfWeek() != DayOfWeek.SATURDAY && (getDate().getDayOfWeek() != DayOfWeek.SUNDAY) && (stopTime.minusMinutes(startTime.getHour() * 60L + startTime.getMinute()).getHour() < 8)))
            return stopTime.minusMinutes(startTime.getHour() * 60L + startTime.getMinute());
        return LocalTime.of(0, 0);
    }
}
