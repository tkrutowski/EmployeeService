package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.DayOffTypeDto;
import net.focik.hr.employee.api.dto.WorkTimeDto;
import net.focik.hr.employee.domain.workTimeRecords.DayOff;
import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;
import net.focik.hr.employee.domain.workTimeRecords.Illness;
import net.focik.hr.employee.domain.workTimeRecords.Work;
import net.focik.hr.employee.domain.workTimeRecords.share.DayOffType;
import net.focik.hr.employee.domain.workTimeRecords.share.WorkType;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class ApiWorkTimeMapper {



    public WorkTimeDto toDto(IWorkTime workTime){

        WorkTimeDto workTimeDto = null;// = new WorkTimeDto();

        if (workTime instanceof DayOff) {
            workTimeDto = WorkTimeDto.builder()
                    .date(((DayOff) workTime).getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .dayOfWeek(((DayOff) workTime).getDate().format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl","PL"))))
                    .startTime("7:00")
                    .stopTime("15:00")
                    .workTimeAll(workTime.WorkTimeAll().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workTime50(workTime.WorkTime50().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workTime100(workTime.WorkTime100().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workType(WorkType.DAY_OFF)
                    .idEmployee(((DayOff) workTime).getIdEmployee())
                    .isHoliday(false)
                    .build();
        } else if (workTime instanceof Illness) {
            workTimeDto = WorkTimeDto.builder()
                    .date(((Illness) workTime).getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .dayOfWeek(((Illness) workTime).getDate().format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl","PL"))))
                    .startTime("7:00")
                    .stopTime("15:00")
                    .workTimeAll(workTime.WorkTimeAll().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workTime50(workTime.WorkTime50().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workTime100(workTime.WorkTime100().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workType(WorkType.ILLNESS)
                    .idEmployee(((Illness) workTime).getIdEmployee())
                    .isHoliday(false)
                    .build();
        } else if (workTime instanceof Work) {
            workTimeDto = WorkTimeDto.builder()
                    .date(((Work) workTime).getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .dayOfWeek(((Work) workTime).getDate().format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl","PL"))))
                    .startTime(((Work) workTime).getStartTime().format(DateTimeFormatter.ofPattern("H:mm")))
                    .stopTime(((Work) workTime).getStopTime().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workTimeAll(workTime.WorkTimeAll().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workTime50(workTime.WorkTime50().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workTime100(workTime.WorkTime100().format(DateTimeFormatter.ofPattern("H:mm")))
                    .workType(WorkType.WORK)
                    .idEmployee(((Work) workTime).getIdEmployee())
                    .isHoliday(false)
                    .build();
        }

        return workTimeDto;
    }
}
