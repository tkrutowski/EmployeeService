package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EmployeeCommandFacade {

    EmployeeCommandService employeeCommandService;
    //RateRegularCommandService rateRegularCommandService;

    public Employee getEmployee(Integer id){
        return employeeCommandService.findEmployeeById(id);
    }

    public Integer addEmployee(Employee employee){
        return employeeCommandService.addEmployee(employee);
    }

//    Integer addRateRegular(RateRegular rateRegular, Integer idEmployee){
//        return rateRegularCommandService.addRateRegular(rateRegular, idEmployee);
//    }


}
