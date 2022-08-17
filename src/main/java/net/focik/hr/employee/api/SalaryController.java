package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.SalaryDto;
import net.focik.hr.employee.api.mapper.ApiSalaryMapper;
import net.focik.hr.employee.domain.port.primary.CalculateSalaryUseCase;
import net.focik.hr.employee.domain.salary.Salary;
import net.focik.hr.employee.domain.utils.PrivilegeHelper;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static net.focik.hr.employee.domain.utils.PrivilegeConstant.AUTHORITIES;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/salary")
//@CrossOrigin
class SalaryController {

    private CalculateSalaryUseCase calculateSalaryUseCase;
    private ApiSalaryMapper salaryMapper;

    @GetMapping("/{idEmployee}")
    ResponseEntity<SalaryDto> calculateSalaryByEmployeeIdAndDate(@PathVariable int idEmployee,
                                                                 @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                 @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try calculate salary for employee id: " + idEmployee + " in " + date.toString());

        final List<String> accessRole = List.of("ROLE_ADMIN", "HR_SALARIES_READ_ALL","HR_SALARIES_READ");

        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
            throw new AccessDeniedException();
        }

        if (!isValid(idEmployee, date))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

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
