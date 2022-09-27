package net.focik.hr.employee.domain.loans.port.primary;

import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.share.LoanStatus;

public interface UpdateLoanUseCase {
    void updateLoanStatus(int idLoan, LoanStatus loanStatus);

    Integer updateLoan(Loan loan);

    Integer updateLoanInstallment(LoanInstallment loanInstallment);
}
