package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(Integer id);

    Integer add(Employee e);
}
