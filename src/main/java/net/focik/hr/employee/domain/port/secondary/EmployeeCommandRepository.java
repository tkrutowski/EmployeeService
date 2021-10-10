package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.Employee;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeCommandRepository {
    Integer add(Employee e);
}
