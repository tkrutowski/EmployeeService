package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.SalaryDto;
import net.focik.hr.employee.api.mapper.ApiSalaryMapper;
import net.focik.hr.employee.domain.port.primary.CalculateSalaryUseCase;
import net.focik.hr.employee.domain.salary.Salary;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/salary")
@CrossOrigin
class SalaryController {

    private CalculateSalaryUseCase calculateSalaryUseCase;
    private ApiSalaryMapper salaryMapper;

//    @CrossOrigin(origins = "http://localhost:8080", methods = RequestMethod.GET)
    @GetMapping("/{idEmployee}")
    ResponseEntity<SalaryDto> calculateSalaryByEmployeeIdAndDate(@PathVariable int idEmployee, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        int i = 0;
        log.info("Try calculate salary for employee id: " + idEmployee + " in " + date.toString());

//        if (!isValid(idEmployee, date))
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        Salary calculatedSalary = calculateSalaryUseCase.calculate(idEmployee, date);

        log.info(calculatedSalary != null ? "Calculate salary for idEmployee = " + idEmployee : "Can't calculate salary for idEmployee = " + idEmployee);

        if (calculatedSalary == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(salaryMapper.toDto(calculatedSalary), HttpStatus.OK);
    }

    private boolean isValid(int idEmployee, LocalDate date) {
        if(idEmployee < 1)
            return false;
        if (date == null)
            return false;
        if(date instanceof LocalDate == false)
            return false;
        return true;
    }

}
