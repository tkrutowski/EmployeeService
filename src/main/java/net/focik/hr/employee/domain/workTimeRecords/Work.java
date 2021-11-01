package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class Work extends WorkTime implements IWorkTime {

    private LocalTime stopTime;
    private LocalTime startTime;

    public Work(int idEmployee, LocalDate date,  LocalTime startTime, LocalTime stopTime) {
        super(idEmployee, date);
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    @Override
    public LocalTime WorkTimeAll() {
        LocalTime localTime = stopTime.minusMinutes(startTime.getHour() * 60 + startTime.getMinute());
        return localTime;
//        return time.minusHours(startTime.getHour());
    }

    @Override
    public LocalTime WorkTime50() {
        if (getDate().getDayOfWeek() != DayOfWeek.SATURDAY && getDate().getDayOfWeek() != DayOfWeek.SUNDAY) {
            if (WorkTimeAll().getHour() >= 8)
                return WorkTimeAll().minusHours(8);
            else//jeżeli poniżej 8 godzin (żeby nie wyświetlało godzin ulemnych
                return LocalTime.of(0, 0, 0);
        } else
            return LocalTime.of(0, 0, 0);
    }

    @Override
    public LocalTime WorkTime100() {
         if (getDate().getDayOfWeek() == DayOfWeek.SATURDAY || getDate().getDayOfWeek() == DayOfWeek.SUNDAY)
             return stopTime.minusMinutes(startTime.getHour() * 60 + startTime.getMinute());

        return LocalTime.of(0, 0);
    }
}
