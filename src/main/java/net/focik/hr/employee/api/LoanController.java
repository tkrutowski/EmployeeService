package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.BasicDto;
import net.focik.hr.employee.api.dto.LoanDto;
import net.focik.hr.employee.api.dto.LoanInstallmentDto;
import net.focik.hr.employee.api.mapper.ApiLoanMapper;
import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.loans.port.primary.AddLoanUseCase;
import net.focik.hr.employee.domain.loans.port.primary.DeleteLoanUseCase;
import net.focik.hr.employee.domain.loans.port.primary.GetLoanUseCase;
import net.focik.hr.employee.domain.loans.port.primary.UpdateLoanUseCase;
import net.focik.hr.employee.domain.share.LoanStatus;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.HttpResponse;
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
@RequestMapping("/api/employee/loan")
//@CrossOrigin
class LoanController {

    private final GetLoanUseCase getLoanUseCase;
    private final AddLoanUseCase addLoanUseCase;
    private final UpdateLoanUseCase updateLoanUseCase;
    private final DeleteLoanUseCase deleteLoanUseCase;
    private final ApiLoanMapper apiLoanMapper;


    //
    //LOAN
    //
    @GetMapping("/status")
    ResponseEntity<List<LoanDto>> getLoansByStatus(@RequestParam(value = "status") LoanStatus loanStatus,
                                                   @RequestParam(value = "installment") boolean installment,
                                                   @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get loans with status: " + loanStatus);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<Loan> loans = getLoanUseCase.getLoansByStatus(loanStatus, installment);

        return new ResponseEntity<>(loans.stream()
                .map(apiLoanMapper::toDto)
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @GetMapping("/{idLoan}")
    ResponseEntity<LoanDto> getLoanById(@PathVariable int idLoan,
                                        @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get loan by id: " + idLoan);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_READ_ALL, HR_LOAN_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Loan loan = getLoanUseCase.getLoanById(idLoan, true);

        return new ResponseEntity<>(apiLoanMapper.toDto(loan), HttpStatus.OK);
    }

    @GetMapping("/{idEmployee}/status")
    ResponseEntity<List<LoanDto>> getLoansByEmployeeAndStatus(@PathVariable int idEmployee,
                                                              @RequestParam(value = "status") LoanStatus loanStatus,
                                                              @RequestParam(value = "installment") boolean installment,
                                                              @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get loans for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<Loan> loans = getLoanUseCase.getLoansByEmployee(idEmployee, loanStatus, installment);

        return new ResponseEntity<>(loans.stream()
                .map(apiLoanMapper::toDto)
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addLoan(@RequestBody LoanDto loanDto,
                                           @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try add new loan.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Loan loan = apiLoanMapper.toDomain(loanDto);
        Integer result = addLoanUseCase.addLoan(loan);

        log.info(result > 0 ? "Loan added with id = " + result : "No loan added!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Integer> updateLoan(@RequestBody LoanDto loanDto,
                                              @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try update loan.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Loan loan = apiLoanMapper.toDomain(loanDto);
        Integer result = updateLoanUseCase.updateLoan(loan);

        log.info(result > 0 ? "Loan updated with id = " + result : "No loan updated!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<HttpResponse> updateLoanStatus(@PathVariable int id, @RequestBody BasicDto basicDto,
                                                         @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try update employment status.");

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        updateLoanUseCase.updateLoanStatus(id, LoanStatus.valueOf(basicDto.getValue()));
        return response(HttpStatus.OK, "Zaaktualizowano status pożyczki.");
    }

    @DeleteMapping("/{idLoan}")
    public ResponseEntity<HttpResponse> deleteLoan(@PathVariable int idLoan,
                                                   @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try to delete loan with id: " + idLoan);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_DELETE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        deleteLoanUseCase.deleteLoanById(idLoan);

        log.info("Deleted loan with id = " + idLoan);

        return response(HttpStatus.NO_CONTENT, "Pożyczka usunięta.");
    }

    //-----------------------------------------------------------------------------------------------------------
    //LOAN INSTALLMENT
    //
    @GetMapping("/installment/{idEmployee}/all")
    ResponseEntity<List<LoanInstallmentDto>> getLoanInstallmentByEmployeeAndDate(@PathVariable int idEmployee,
                                                                                 @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                                 @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get all loan installment by date for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_READ_ALL, HR_LOAN_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<LoanInstallment> loanInstallments = getLoanUseCase.getLoanInstallments(idEmployee, date).stream()
                .filter(loanInstallment -> !loanInstallment.isOwnRepayment())
                .collect(Collectors.toList());

        return new ResponseEntity<>(loanInstallments.stream()
                .map(apiLoanMapper::toDto)
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @GetMapping("/installment/{idLoan}")
    ResponseEntity<List<LoanInstallmentDto>> getLoanInstallmentsByLoan(@PathVariable int idLoan,
                                                                       @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get all loan installment by loan id: " + idLoan);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_READ_ALL, HR_LOAN_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<LoanInstallment> loanInstallments = getLoanUseCase.getLoanInstallments(idLoan);

        return new ResponseEntity<>(loanInstallments.stream()
                .map(apiLoanMapper::toDto)
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @PostMapping("/installment")
    public ResponseEntity<Integer> addLoanInstallment(@RequestBody LoanInstallmentDto loanInstallmentDto,
                                                      @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try add new loan installment.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        LoanInstallment loanInstallment = apiLoanMapper.toDomain(loanInstallmentDto);
        Integer result = addLoanUseCase.addLoanInstallment(loanInstallment);

        log.info(result > 0 ? "Loan installment added with id = " + result : "No addition added!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/installment")
    public ResponseEntity<Integer> updateLoanInstallment(@RequestBody LoanInstallmentDto loanInstallmentDto,
                                                         @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try update loan installment.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        LoanInstallment loanInstallment = apiLoanMapper.toDomain(loanInstallmentDto);
        Integer result = updateLoanUseCase.updateLoanInstallment(loanInstallment);

        log.info(result > 0 ? "Loan installment updated with id = " + result : "No loanInstallment updated!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/installment/{idLoanInstallment}")
    public ResponseEntity<HttpResponse> deleteLoanInstallment(@PathVariable int idLoanInstallment,
                                                              @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try to delete loan installment with id: " + idLoanInstallment);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_LOAN_DELETE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        deleteLoanUseCase.deleteLoanInstallmentById(idLoanInstallment);

        log.info("Deleted loan with id = " + idLoanInstallment);

        return response(HttpStatus.NO_CONTENT, "Pożyczka usunięta.");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase(), message);
        return new ResponseEntity<>(body, status);
    }
}