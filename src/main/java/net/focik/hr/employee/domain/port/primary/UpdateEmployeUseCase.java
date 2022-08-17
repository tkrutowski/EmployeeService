package net.focik.hr.employee.domain.port.primary;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.share.EmploymentStatus;

public interface UpdateEmployeUseCase {
    void updateEmploymentStatus(int idEmployee, EmploymentStatus employmentStatus);
    Employee updateEmployee(Employee employee);
}
