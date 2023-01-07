package net.focik.hr.employee.domain.worktimerecords.port.primary;

import net.focik.hr.employee.domain.worktimerecords.share.WorkType;

import java.time.LocalDate;

public interface DeleteWorkTimeUseCase {
    void deleteWorkTime(Integer idEmployee, LocalDate date, WorkType workType);
}
