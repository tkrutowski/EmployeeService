package net.focik.hr.employee.domain.port.primary;

import net.focik.hr.employee.domain.worktimerecords.IWorkTime;

public interface AddWorkTimeUseCase {
    void addWorkTime(IWorkTime workTime);
}
