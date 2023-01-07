package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.SalaryDto;
import net.focik.hr.employee.api.mapper.ApiSalaryMapper;
import net.focik.hr.employee.domain.salary.Salary;
import net.focik.hr.employee.domain.salary.port.primary.CalculateSalaryUseCase;
import net.focik.hr.employee.domain.salary.port.primary.PrintSalaryUseCase;
import net.focik.hr.employee.domain.share.PrintTypePdf;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.HttpResponse;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static net.focik.hr.utils.privileges.PrivilegeHelper.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/salary")
//@CrossOrigin
class SalaryController {

    private final CalculateSalaryUseCase calculateSalaryUseCase;
    private final ApiSalaryMapper salaryMapper;
    private final PrintSalaryUseCase printSalaryUseCase;

    @GetMapping("/{idEmployee}")
    ResponseEntity<SalaryDto> calculateSalaryByEmployeeIdAndDate(@PathVariable int idEmployee,
                                                                 @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                 @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try calculate salary for employee id: " + idEmployee + " in " + date.toString());

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_SALARIES_READ_ALL, HR_SALARIES_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        if (isNotValid(idEmployee, date))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Salary calculatedSalary = calculateSalaryUseCase.calculate(idEmployee, date);

        log.info(calculatedSalary != null ? "Calculate salary for idEmployee = " + idEmployee : "Can't calculate salary for idEmployee = " + idEmployee);

        if (calculatedSalary == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(salaryMapper.toDto(calculatedSalary), HttpStatus.OK);
    }
    @GetMapping("/print")
    public ResponseEntity<?> downloadFile(@RequestParam List<Integer> idEmployees,
                                          @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                          @RequestParam("print") PrintTypePdf printTypePdf,
                                          @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Try print {} for date {}", printTypePdf, date);
        List<String> accessRole = null;
        switch (printTypePdf) {
            case SALARY_SHEET:
                accessRole = List.of(ROLE_ADMIN, HR_SALARIES_READ_ALL);
                break;
            case WORK_SHEET:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + printTypePdf);
        }

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        String fileName = printSalaryUseCase.createPdf(idEmployees, date, printTypePdf);

        Resource resource;
        try {
            Path path = Path.of(fileName);
            resource = new UrlResource(path.toUri());
        } catch (IOException e) {
            return response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase(), message);
        return new ResponseEntity<>(body, status);
    }

    private boolean isNotValid(int idEmployee, LocalDate date) {
        return (idEmployee < 1 || date == null);
    }
}