package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
//@NoArgsConstructor
@RequestMapping("/api/employee")
class EmployeeController {

    private EmployeeFacade employeeFacade;

    @PostMapping
    public Integer addEmployee(@RequestBody Employee gasConnectionDbDto){
        return employeeFacade.addEmployee(gasConnectionDbDto);
    }

}
