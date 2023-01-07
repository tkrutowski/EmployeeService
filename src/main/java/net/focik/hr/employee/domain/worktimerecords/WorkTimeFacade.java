package net.focik.hr.employee.domain.worktimerecords;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.EmployeeQueryFacade;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.PrintTypePdf;
import net.focik.hr.employee.domain.worktimerecords.share.WorkType;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class WorkTimeFacade {

    private final WorkService workService;
    private final DaysToWorkService daysToWorkService;
    private final EmployeeQueryFacade employeeFacade;

    public void addWorkTime(IWorkTime workTime) {
        workService.addWorkTime(workTime);
    }

    /**
     * Return a list of worktime (work, illness, dayOff) of a given employee in a given date.
     *
     * @param idEmployee id employee
     * @param date       time frame to search (year, month)
     * @return List of work, illness and dayOff
     */
    public List<IWorkTime> getAllByEmployeeIdAndDate(Integer idEmployee, LocalDate date) {
        return workService.findAllByIdEmployeeAndDate(idEmployee, date);
    }

    public DaysToWork getDaysToWorkByDate(Integer year, Integer month) {
        return daysToWorkService.findDaysToWorkByDate(year, month);
    }

    public Map<Integer, String> getDayOffTypeMap() {
        return workService.getDayOffTypeMap();
    }

    public void deleteWorkTime(Integer idEmployee, LocalDate date, WorkType workType) {
    workService.deleteWorkTime(idEmployee, date, workType);
    }


    public String createPdf(List<Integer> idEmployees, LocalDate date, PrintTypePdf printTypePdf) {
        DaysToWork daysToWork = daysToWorkService.findDaysToWorkByDate(date.getYear(), date.getMonthValue());
        List<EmployeeQueryBasicDto> employees = employeeFacade.getAllEmployeesByEmploymentStatus(EmploymentStatus.HIRED).stream()
        //        List<String> employees = employeeFacade.getAllEmployeesByEmploymentStatus(EmploymentStatus.HIRED).stream()
                .filter(dto -> idEmployees.contains(dto.getId()))
//                .map(basicDto -> basicDto.getFirstName() + " " + basicDto.getLastName())
                .collect(Collectors.toList());
        String result = null;
        switch (printTypePdf) {
            case ATTENDANCE:
                result = workService.createAttendancePdf(employees, date, daysToWork);
                break;
            case TIME_SHEET:
                result = workService.createTimeSheetPdf(employees, date, daysToWork);
                break;
            case SALARY_SHEET:
                break;
            case WORK_SHEET:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + printTypePdf);
        }
        return result;
    }
}
