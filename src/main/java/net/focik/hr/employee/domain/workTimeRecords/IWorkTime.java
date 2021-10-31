package net.focik.hr.employee.domain.workTimeRecords;

import java.time.LocalTime;

public interface IWorkTime {
    LocalTime WorkTimeAll();
    LocalTime WorkTime50();
    LocalTime WorkTime100();

    default long TotalMinutes (LocalTime time){
        long result = 0;
        if(time == null)
            throw new NullPointerException("LocalTime can't be null");

        result = time.getHour() * 60;
        result += time.getMinute();
        return result;
    }
//    bool IsAlreadyInDataBase();
//    int GetDay();
}
