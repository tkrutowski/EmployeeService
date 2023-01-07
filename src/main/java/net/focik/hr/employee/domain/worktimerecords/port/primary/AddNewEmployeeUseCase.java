package net.focik.hr.employee.domain.worktimerecords.port.primary;

import net.focik.hr.employee.domain.Employee;

public interface AddNewEmployeeUseCase {
    Integer addEmployee(Employee employee);
}
