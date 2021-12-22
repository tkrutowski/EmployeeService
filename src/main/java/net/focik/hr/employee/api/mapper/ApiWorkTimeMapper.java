package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.WorkTimeDto;
import net.focik.hr.employee.domain.workTimeRecords.DayOff;
import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;
import net.focik.hr.employee.domain.workTimeRecords.Illness;
import net.focik.hr.employee.domain.workTimeRecords.Work;
import net.focik.hr.employee.domain.workTimeRecords.share.DayOffType;
import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;
import net.focik.hr.employee.domain.workTimeRecords.share.WorkType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

@Component
public class ApiWorkTimeMapper {



    public WorkTimeDto toDto(IWorkTime workTime){

        WorkTimeDto workTimeDto = null;

        if (workTime instanceof DayOff) {
            workTimeDto = WorkTimeDto.builder()
                    .date(((DayOff) workTime).getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .dayOfWeek(((DayOff) workTime).getDate().format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl","PL"))))
                    .startTime("Urlop")
                    .stopTime(((DayOff) workTime).getDayOffType().getDbValue())
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
                    .startTime("Zasiłek")
                    .stopTime(((Illness) workTime).getIllnessType().getDbValue())
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

    public IWorkTime toDomain(Map list, WorkType workType){

        IWorkTime workTime = null;

        switch (workType) {
            case WORK:
               workTime =  new Work((int)list.get("idEmployee"),
                       LocalDate.parse(list.get("date").toString()),
                        LocalTime.parse(list.get("startTime").toString()),
                        LocalTime.parse(list.get("stopTime").toString()));
                break;
            case ILLNESS:
                workTime = new Illness((int)list.get("idEmployee"),
                        LocalDate.parse(list.get("date").toString()),
                        IllnessType.fromIdValue ((Integer) list.get("idIllnessType")));
                break;
            case DAY_OFF:
                workTime = new DayOff((int)list.get("idEmployee"),
                        LocalDate.parse(list.get("date").toString()),
                        DayOffType.fromIdValue ((Integer) list.get("idDayOffType")));
                break;
        }

        return workTime;
    }
}
