package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeCommandFacade;
import net.focik.hr.employee.domain.port.primary.AddNewEmployeeUseCase;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AddEmployeeService implements AddNewEmployeeUseCase {
    EmployeeCommandFacade commandFacade;

    @Override
    public Integer addEmployee(Employee employee) {
        return commandFacade.addEmployee(employee);
    }
}
