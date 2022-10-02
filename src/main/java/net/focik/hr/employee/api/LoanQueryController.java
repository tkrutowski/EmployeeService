package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.LoanInstallmentDto;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.loans.LoanQueryFacade;
import net.focik.hr.employee.domain.share.LoanStatus;
import net.focik.hr.employee.query.LoanInstallmentQueryDto;
import net.focik.hr.employee.query.LoanQueryDto;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static net.focik.hr.utils.privileges.PrivilegeHelper.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/loan/query")
//@CrossOrigin
class LoanQueryController {

    private final LoanQueryFacade loanQueryFacade;

    @GetMapping("/{id}")
    ResponseEntity<List<LoanQueryDto>> getAllLoansBasic(@PathVariable int id,
                                                        @RequestParam LoanStatus status,
                                                        @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try find all loans by employee: " + id + " and LoanStatus = " + status);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_READ_ALL, HR_LOAN_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<LoanQueryDto> loans = loanQueryFacade.findLoansByEmployee(id, status);
        log.info("Found " + loans.size() + " loans.");

        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    //-----------------------------------------------------------------------------------------------------------
    //LOAN INSTALLMENT
    //
    @GetMapping("/installment/{idEmployee}/all")
    ResponseEntity<List<LoanInstallmentQueryDto>> getLoanInstallmentByEmployeeAndDate(@PathVariable int idEmployee,
                                                                                 @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                                 @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get all loan installment by date for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_READ_ALL, HR_LOAN_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<LoanInstallmentQueryDto> loanInstallments = loanQueryFacade.findLoanInstallmentByEmployeeIdAndDate(idEmployee, date);

        return new ResponseEntity<>(loanInstallments, HttpStatus.OK);
    }
}