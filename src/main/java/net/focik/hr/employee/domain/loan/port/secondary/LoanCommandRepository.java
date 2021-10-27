package net.focik.hr.employee.domain.loan.port.secondary;

import net.focik.hr.employee.domain.loan.Loan;
import net.focik.hr.employee.domain.loan.LoanInstallment;
import org.springframework.stereotype.Component;

@Component
public interface LoanCommandRepository {
    Integer addLoan(Loan loan);

    Integer addLoanInstallment(LoanInstallment loanInstallment);
}
