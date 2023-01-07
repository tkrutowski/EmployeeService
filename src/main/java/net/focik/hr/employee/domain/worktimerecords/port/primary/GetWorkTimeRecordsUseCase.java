package net.focik.hr.employee.domain.worktimerecords.port.primary;

import net.focik.hr.employee.domain.worktimerecords.IWorkTime;

import java.time.LocalDate;
import java.util.List;

public interface GetWorkTimeRecordsUseCase {
    List<IWorkTime> getWorkTimeByEmployeeAndDate(Integer idEmployee, LocalDate date);
}
