package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.query.EmployeeQueryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class EmployeeQueryFacade {

    EmployeeQueryService employeeQueryService;


    public EmployeeQueryDto getEmployee(Integer id){
        return employeeQueryService.findEmployeeById(id);
    }

    public List<EmployeeQueryDto> getAllEmployee(){
        return employeeQueryService.findAll();
    }
}
