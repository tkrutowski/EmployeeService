package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class WorkTimeFacade {

    WorkService workService;

    public Integer addWorkTime(IWorkTime workTime) {
        return workService.addWorkTime(workTime);
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


}
