package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.domain.loans.LoanQueryFacade;
import net.focik.hr.employee.domain.share.LoanStatus;
import net.focik.hr.employee.query.LoanQueryDto;
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
@RequestMapping("/api/employee/loan/query")
//@CrossOrigin
class LoanQueryController {

    private final LoanQueryFacade loanFacade;

    @GetMapping("/{id}")
    ResponseEntity<List<LoanQueryDto>> getAllLoansBasic(@PathVariable int id,
                                                        @RequestParam LoanStatus status,
                                                        @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try find all loans by employee: " + id + " and LoanStatus = " + status);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_READ_ALL, HR_LOAN_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<LoanQueryDto> loans = loanFacade.findLoansByEmployee(id, status);
        log.info("Found " + loans.size() + " loans.");

        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
}
