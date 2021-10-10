package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EmployeeCommandFacade {

    EmployeeCommandService employeeCommandService;

    public Integer addEmployee(Employee employee){
        return employeeCommandService.addEmployee(employee);
    }

}
