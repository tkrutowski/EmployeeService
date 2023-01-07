package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.WorkTimeDto;
import net.focik.hr.employee.domain.worktimerecords.DayOff;
import net.focik.hr.employee.domain.worktimerecords.IWorkTime;
import net.focik.hr.employee.domain.worktimerecords.Illness;
import net.focik.hr.employee.domain.worktimerecords.Work;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;
import net.focik.hr.employee.domain.worktimerecords.share.IllnessType;
import net.focik.hr.employee.domain.worktimerecords.share.WorkType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class ApiWorkTimeMapper {

    public static final String ID_EMPLOYEE = "idEmployee";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String TIME_FORMAT = "H:mm";

    public WorkTimeDto toDto(IWorkTime workTime) {

        WorkTimeDto workTimeDto = null;

        if (workTime instanceof DayOff) {
            workTimeDto = WorkTimeDto.builder()
                    .date(((DayOff) workTime).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .dayOfWeek(((DayOff) workTime).getDate().format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl", "PL"))))
                    .startTime("Urlop")
                    .stopTime(((DayOff) workTime).getDayOffType().getDbValue())
                    .workTimeAll(workTime.workTimeAll().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workTime50(workTime.workTime50().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workTime100(workTime.workTime100().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workType(WorkType.DAY_OFF)
                    .idEmployee(((DayOff) workTime).getIdEmployee())
                    .isHoliday(false)
                    .idDayOffType(((DayOff) workTime).getDayOffType().getIdValue())
                    .build();
        } else if (workTime instanceof Illness) {
            workTimeDto = WorkTimeDto.builder()
                    .date(((Illness) workTime).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .dayOfWeek(((Illness) workTime).getDate().format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl", "PL"))))
                    .startTime("Zasiłek")
                    .stopTime(((Illness) workTime).getIllnessType().getDbValue())
                    .workTimeAll(workTime.workTimeAll().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workTime50(workTime.workTime50().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workTime100(workTime.workTime100().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workType(WorkType.ILLNESS)
                    .idEmployee(((Illness) workTime).getIdEmployee())
                    .isHoliday(false)
                    .idIllnessType(((Illness) workTime).getIllnessType().getIdValue())
                    .build();
        } else if (workTime instanceof Work) {
            workTimeDto = WorkTimeDto.builder()
                    .date(((Work) workTime).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .dayOfWeek(((Work) workTime).getDate().format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl", "PL"))))
                    .startTime(((Work) workTime).getStartTime().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .stopTime(((Work) workTime).getStopTime().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workTimeAll(workTime.workTimeAll().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workTime50(workTime.workTime50().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workTime100(workTime.workTime100().format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                    .workType(WorkType.WORK)
                    .idEmployee(((Work) workTime).getIdEmployee())
                    .isHoliday(false)
                    .build();
        }
        return workTimeDto;
    }

    public IWorkTime toDomain(WorkTimeDto workTimeDto, WorkType workType) {
        IWorkTime workTime = null;

        switch (workType) {
            case WORK:
                workTime = new Work(workTimeDto.getIdEmployee(),
                        LocalDate.parse(workTimeDto.getDate()),
                        LocalTime.parse(workTimeDto.getStartTime()),
                        LocalTime.parse(workTimeDto.getStopTime()));
                break;
            case ILLNESS:
                workTime = new Illness(workTimeDto.getIdEmployee(),
                        LocalDate.parse(workTimeDto.getDate()),
                        IllnessType.fromIdValue(workTimeDto.getIdIllnessType()));
                break;
            case DAY_OFF:
                workTime = new DayOff(workTimeDto.getIdEmployee(),
                        LocalDate.parse(workTimeDto.getDate()),
                        DayOffType.fromIdValue(workTimeDto.getIdDayOffType()));
                break;
        }

        return workTime;
    }
}