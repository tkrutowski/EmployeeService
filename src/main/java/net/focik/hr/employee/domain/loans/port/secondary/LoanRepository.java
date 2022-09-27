package net.focik.hr.employee.domain.loans.port.secondary;

import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public interface LoanRepository {
    Integer saveLoan(Loan loan);

    Integer saveLoanInstallment(LoanInstallment loanInstallment);

    Optional<Loan> findLoanById(Integer id);

    Optional<LoanInstallment> findLoanInstallmentById(Integer id);

    List<Loan> findLoanByEmployeeId(Integer idEmployee);

    List<Loan> findAll();

    List<LoanInstallment> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date);

    List<LoanInstallment> findLoanInstallmentByLoanId(Integer loanId);

    void deleteLoanById(int idLoan);

    void deleteLoanInstallmentById(int idLoanInstallment);

    void deleteLoanInstallmentByIdLoan(int idLoan);
}
