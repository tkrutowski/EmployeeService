package net.focik.hr.employee.domain.loans.port.secondary;

import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public interface LoanRepository {
    Integer addLoan(Loan loan);

    Integer addLoanInstallment(LoanInstallment loanInstallment);

    Optional<Loan> findById(Integer id);

    List<LoanInstallment> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date);
}
