package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.BasicDto;
import net.focik.hr.employee.api.dto.EmployeeDto;
import net.focik.hr.employee.api.dto.EmployeeTypeDto;
import net.focik.hr.employee.api.dto.EmployeeWorktimeDto;
import net.focik.hr.employee.api.mapper.ApiEmployeeMapper;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.primary.AddNewEmployeeUseCase;
import net.focik.hr.employee.domain.port.primary.DeleteEmployeeUseCase;
import net.focik.hr.employee.domain.port.primary.UpdateEmployeUseCase;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.WorkTime;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.HttpResponse;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.focik.hr.utils.privileges.PrivilegeHelper.*;
import static org.springframework.http.HttpStatus.OK;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
@CrossOrigin
class EmployeeController {

    private AddNewEmployeeUseCase addNewEmployeeUseCase;
    private ApiEmployeeMapper mapper;
    private DeleteEmployeeUseCase deleteEmployeeUseCase;
    private UpdateEmployeUseCase updateEmployeUseCase;

    @PostMapping
    public ResponseEntity<Integer> addEmployee(@RequestBody EmployeeDto employeeDto,
                                               @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try add new employee.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_EMPLOYEE_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Employee employee = mapper.toDomain(employeeDto);
        Integer result = addNewEmployeeUseCase.addEmployee(employee);

        log.info(result > 0 ? "Employee added with id = " + result : "No employee added!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,
                                                      @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try update employee.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_EMPLOYEE_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Employee employee = updateEmployeUseCase.updateEmployee(mapper.toDomain(employeeDto));
        return new ResponseEntity<>(mapper.toDto(employee), OK);
    }

    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<HttpResponse> deleteEmployee(@PathVariable int idEmployee,
                                                       @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try delete employee with id: " + idEmployee);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_EMPLOYEE_DELETE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        deleteEmployeeUseCase.deleteEmployeeById(idEmployee);

        log.info("Deleted employee with id = " + idEmployee);

        return response(HttpStatus.NO_CONTENT, "Pracownik usunięty.");
    }

    @GetMapping("/employeetype")
    ResponseEntity<List<EmployeeTypeDto>> getEmployeeTypesView() {
        List<EmployeeTypeDto> types = Arrays.stream(EmployeeType.values())
                .map(t -> new EmployeeTypeDto(t.toString(), t.getViewValue()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @PutMapping("/employeetype/{id}")
    public ResponseEntity<HttpResponse> updateEmploymentStatus(@PathVariable int id, @RequestBody BasicDto basicDto,
                                                               @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try update employment status.");

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_EMPLOYEE_WRITE_ALL);

//        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
//            throw new AccessDeniedException();
//        }

        updateEmployeUseCase.updateEmploymentStatus(id, EmploymentStatus.valueOf(basicDto.getValue()));
        return response(HttpStatus.OK, "Zaaktualizowano status pracownika.");
    }

    @GetMapping("/employeeworktime")
    ResponseEntity<List<EmployeeWorktimeDto>> getEmployeeWorktimes() {
        List<EmployeeWorktimeDto> types = Arrays.stream(WorkTime.values())
                .map(t -> new EmployeeWorktimeDto(t.toString(), t.getViewValue()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase(), message);
        return new ResponseEntity<>(body, status);
    }

}
