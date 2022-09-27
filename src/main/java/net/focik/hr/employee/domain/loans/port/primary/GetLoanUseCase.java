package net.focik.hr.employee.domain.loans.port.primary;

import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.share.LoanStatus;

import java.time.LocalDate;
import java.util.List;

public interface GetLoanUseCase {

    LoanInstallment getLoanInstallment(Integer idLoanInstallment);

    List<Loan> getLoansByEmployee(Integer idEmployee, boolean withInstallment);

    List<Loan> getLoansByEmployee(Integer idEmployee, LoanStatus loanStatus, boolean withInstallment);

    List<LoanInstallment> getLoanInstallments(Integer idEmployee, LocalDate date);

    List<LoanInstallment> getLoanInstallments(Integer idLoan);

    Loan getLoanById(int idLoan, boolean withInstallment);

    List<Loan> getLoansByStatus(LoanStatus loanStatus, boolean withInstallment);
}
