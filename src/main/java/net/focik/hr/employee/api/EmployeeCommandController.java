package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.EmployeeCommandFacade;
import net.focik.hr.employee.domain.port.primary.AddNewEmployeeUseCase;
import net.focik.hr.employee.query.EmployeeQueryDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
class EmployeeCommandController {

    private AddNewEmployeeUseCase addNewEmployeeUseCase;
    private ApiMapper mapper;

    @PostMapping
    public ResponseEntity<Integer> addEmployee(@RequestBody EmployeeDto employeeDto){
        log.info("Try add new employee.");
        Employee employee = mapper.toDomain(employeeDto);
        Integer result = addNewEmployeeUseCase.addEmployee(employee);

        log.info(result > 0 ? "Employee added with id = " + result : "No employee added!");

        if(result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
//
//    @PostMapping("/rate_regular/")
//    public ResponseEntity<Boolean> addRateRegular(@RequestBody RateRegularDto rateRegularDto){
//        log.info("Try add new rate regular.");
//        modelMapper.map(rateRegularDto, );
//        Integer result = addNewEmployeeUseCase.addEmployee(employee);
//
//        log.info(result > 0 ? "Employee added with id = " + result : "No employee added!");
//
//        if(result <= 0)
//            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
//
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }

}
