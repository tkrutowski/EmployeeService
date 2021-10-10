package net.focik.hr.employee.domain.port.primary;

import net.focik.hr.employee.domain.Employee;

public interface AddNewEmployeeUseCase {
    Integer addEmployee(Employee employee);
}
