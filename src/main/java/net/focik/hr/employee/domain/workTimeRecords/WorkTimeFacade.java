package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class WorkTimeFacade {

    WorkService workService;
    DaysToWorkService daysToWorkService;

    public void addWorkTime(IWorkTime workTime) {
        workService.addWorkTime(workTime);
    }

    /**
     * Return a list of worktime (work, illness, dayOff) of a given employee in a given date.
     * @param idEmployee
     * @param date  time frame to search (year, month)
     * @return  List of work, illness and dayOff
     */
    public List<IWorkTime> getAllByEmployeeIdAndDate(Integer idEmployee, LocalDate date) {
        return workService.findAllByIdEmployeeAndDate(idEmployee, date);
    }

    public DaysToWork getDaysToWorkByDate(Integer year, Integer month){
        return daysToWorkService.findDaysToWorkByDate(year, month);
    }

    public Map<Integer, String> getDayOffTypeMap(){
        return workService.getDayOffTypeMap();
    }

}
