package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.domain.EmployeeQueryFacade;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
//@NoArgsConstructor
@RequestMapping("/api/employee/query")
@CrossOrigin
class EmployeeQueryController {

    private EmployeeQueryFacade employeeFacade;


    @GetMapping()
    ResponseEntity<List<EmployeeQueryBasicDto>> getAllEmployeeBasicInfo(@RequestParam EmploymentStatus status){
        int i=0;
        log.info("Try find all employee by EmploymentStatus = " + status);
        List<EmployeeQueryBasicDto> allEmployee = employeeFacade.getAllEmployeesByEmploymentStatus(status);
        log.info("Found " + allEmployee.size() + " employees.");

        return new ResponseEntity<>(allEmployee, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeQueryBasicDto> getById(@PathVariable int id){
        int i=0;
        log.info("Try find employee by id: "+id);
        EmployeeQueryBasicDto employee = employeeFacade.getEmployee(id);

        log.info(employee != null ? "Found employee for id = " + id : "Not found employee for id = " + id);

        if(employee == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(employee, HttpStatus.OK);

    }

}
