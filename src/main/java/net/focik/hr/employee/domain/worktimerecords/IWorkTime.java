package net.focik.hr.employee.domain.worktimerecords;

import java.time.LocalTime;

public interface IWorkTime {
    LocalTime workTimeAll();

    LocalTime workTime50();

    LocalTime workTime100();

    default long totalMinutes(LocalTime time) {
        long result = 0;
        if (time == null)
            throw new NullPointerException("LocalTime can't be null");

        result = time.getHour() * 60L;
        result += time.getMinute();
        return result;
    }
}
