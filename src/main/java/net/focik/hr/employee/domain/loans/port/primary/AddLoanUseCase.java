package net.focik.hr.employee.domain.loans.port.primary;

import net.focik.hr.employee.domain.loans.Loan;

public interface AddLoanUseCase {
    Integer addLoan(Loan advance);
}
