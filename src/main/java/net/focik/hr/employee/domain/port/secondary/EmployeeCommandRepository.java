package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.Employee;

import java.util.Optional;

public interface EmployeeCommandRepository {

    Integer add(Employee e);
}
