package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.api.dto.WorkTimeDto;
import net.focik.hr.employee.api.mapper.ApiWorkTimeMapper;
import net.focik.hr.employee.domain.share.PrintTypePdf;
import net.focik.hr.employee.domain.worktimerecords.DaysToWork;
import net.focik.hr.employee.domain.worktimerecords.IWorkTime;
import net.focik.hr.employee.domain.worktimerecords.WorkTimeFacade;
import net.focik.hr.employee.domain.worktimerecords.port.primary.AddWorkTimeUseCase;
import net.focik.hr.employee.domain.worktimerecords.port.primary.DeleteWorkTimeUseCase;
import net.focik.hr.employee.domain.worktimerecords.port.primary.GetWorkTimeRecordsUseCase;
import net.focik.hr.employee.domain.worktimerecords.port.primary.PrintWorkTimeUseCase;
import net.focik.hr.employee.domain.worktimerecords.share.WorkType;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class WorkTimeApiService implements GetWorkTimeRecordsUseCase, AddWorkTimeUseCase, DeleteWorkTimeUseCase, PrintWorkTimeUseCase {
    WorkTimeFacade workTimeFacade;
    ApiWorkTimeMapper workTimeMapper;

    @Override
    public List<IWorkTime> getWorkTimeByEmployeeAndDate(Integer idEmployee, LocalDate date) {

        return workTimeFacade.getAllByEmployeeIdAndDate(idEmployee, date);

    }

    @Override
    public void addWorkTime(IWorkTime workTime) {
        workTimeFacade.addWorkTime(workTime);
    }

    @Override
    public void deleteWorkTime(Integer idEmployee, LocalDate date, WorkType workType) {
        workTimeFacade.deleteWorkTime(idEmployee, date, workType);
    }

    /**
     * Add empty days to complete the calendar
     *
     * @param workTimes wokTime list
     * @param date date to fill with empty days
     * @return completed and sort by date full month
     */
    public List<WorkTimeDto> addEmptyDays(List<IWorkTime> workTimes, LocalDate date) {
        int daysInMonth = date.lengthOfMonth();
        List<WorkTimeDto> workTimeDtos = workTimes.stream()
                .map(workTime -> workTimeMapper.toDto(workTime))
                .collect(Collectors.toList());

        boolean isExist;
        for (int i = 1; i <= daysInMonth; i++) {
            isExist = false;
            for (WorkTimeDto dto : workTimeDtos) {
                if (Integer.parseInt(dto.getDate().substring(0, 2)) == i) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                workTimeDtos.add(WorkTimeDto.builder()
                        .date(LocalDate.of(date.getYear(), date.getMonth().getValue(), i).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        .dayOfWeek(LocalDate.of(date.getYear(), date.getMonth().getValue(), i).format(DateTimeFormatter.ofPattern("EEEE", new Locale("pl", "PL"))))

                        .build());
            }
        }

        resolveHolidays(workTimeDtos, date);

        return workTimeDtos.stream()
                .sorted(Comparator.comparing(WorkTimeDto::getDate))
                .collect(Collectors.toList());
    }


    private void resolveHolidays(List<WorkTimeDto> workTimeDtos, LocalDate date) {
        DaysToWork daysToWorkByDate = workTimeFacade.getDaysToWorkByDate(date.getYear(), date.getMonth().getValue());

        for (WorkTimeDto dto : workTimeDtos) {
            LocalDate localDate = LocalDate.of(Integer.parseInt(dto.getDate().substring(6)), Integer.parseInt(dto.getDate().substring(3, 5)), Integer.parseInt(dto.getDate().substring(0, 2)));
            dto.setIsHoliday(daysToWorkByDate.getHolidays().keySet().stream()
                    .anyMatch(date1 -> date1.equals(localDate)));
            if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                dto.setIsHoliday(true);
            }
        }
    }

    @Override
    public String createPdf(List<Integer> idEmployees, LocalDate date, PrintTypePdf printTypePdf) {
        return workTimeFacade.createPdf(idEmployees, date, printTypePdf);
    }
}