package net.focik.hr.employee.domain.loans.port.primary;

import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;

public interface AddLoanUseCase {
    Integer addLoan(Loan loan);

    Integer addLoanInstallment(LoanInstallment loanInstallment);
}
