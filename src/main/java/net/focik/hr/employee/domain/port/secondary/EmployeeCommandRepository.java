package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EmployeeCommandRepository {
    Integer add(Employee e);

    Employee update(Employee e);

    Optional<Employee> findById(Integer id);

    List<Employee> findAll();

    void delete(Integer id);
}
