package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EmployeeFacade {

    EmployeeCommandService employeeCommandService;
    RateRegularService rateRegularService;
    RateOvertimeService rateOvertimeService;

    public Integer addEmployee(Employee employee){
        return employeeCommandService.addEmployee(employee);
    }

    public void addRate( RateRegular rate){
        rateRegularService.addRateRegular(rate);
    }

    public void addRateOvertime(Integer idEmployee, RateOvertime rate){
        rateOvertimeService.addRateOvertime(rate);
    }

    public Employee getEmployee(Integer id){
        return employeeCommandService.findEmployeeById(id);
    }
}
