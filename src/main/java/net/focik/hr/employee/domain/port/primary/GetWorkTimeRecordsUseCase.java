package net.focik.hr.employee.domain.port.primary;

import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;

import java.time.LocalDate;
import java.util.List;

public interface GetWorkTimeRecordsUseCase {
    List<IWorkTime> getWorkTimeByEmployeeAndDate(Integer idEmployee, LocalDate date);
}
