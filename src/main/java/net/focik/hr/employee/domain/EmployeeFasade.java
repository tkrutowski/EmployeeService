package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EmployeeFasade {

    EmployeeService employeeService;
    RateService rateService;

    public void addEmployee(Employee employee){
        employeeService.addEmployee(employee);
    }

    public void addRate( RateRegular rate){
        rateService.addRateRegular(rate);
    }

    public void addRateOvertime(Integer idEmployee, RateOvertime rate){
        rateService.addRateOvertime(rate);
    }

    public Employee getEmployee(Integer id){
        return employeeService.findEmployeeById(id);
    }
}
