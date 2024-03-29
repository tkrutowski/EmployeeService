package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.domain.EmployeeQueryFacade;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import net.focik.hr.employee.query.EmployeeQueryPrintDto;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.focik.hr.utils.privileges.PrivilegeHelper.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/query")
//@CrossOrigin
class EmployeeQueryController {

    private final EmployeeQueryFacade employeeFacade;

    @GetMapping()
    ResponseEntity<List<EmployeeQueryBasicDto>> getAllEmployeeBasicInfo(@RequestParam EmploymentStatus status,
                                                                        @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try find all employee by EmploymentStatus = " + status);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_EMPLOYEE_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<EmployeeQueryBasicDto> allEmployee;
        if (status.equals(EmploymentStatus.ALL)) {
            allEmployee = employeeFacade.getAllEmployee();
        } else {
            allEmployee = employeeFacade.getAllEmployeesByEmploymentStatus(status);
        }
        log.info("Found " + allEmployee.size() + " employees.");

        return new ResponseEntity<>(allEmployee, HttpStatus.OK);
    }

    @GetMapping("/print")
    ResponseEntity<List<EmployeeQueryPrintDto>> getAllEmployeePrintInfo(@RequestParam EmploymentStatus status,
                                                                        @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try find all employee by EmploymentStatus = " + status);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_EMPLOYEE_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<EmployeeQueryPrintDto> allEmployee = employeeFacade.getAllEmployeesByEmploymentStatusPrint(status);

        log.info("Found " + allEmployee.size() + " employees.");

        return new ResponseEntity<>(allEmployee, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeQueryBasicDto> getById(@PathVariable int id,
                                                  @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try find employee by id: " + id);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_EMPLOYEE_READ_ALL, HR_EMPLOYEE_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        EmployeeQueryBasicDto employee = employeeFacade.getEmployee(id);

        log.info(employee != null ? "Found employee for id = " + id : "Not found employee for id = " + id);

        if (employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}