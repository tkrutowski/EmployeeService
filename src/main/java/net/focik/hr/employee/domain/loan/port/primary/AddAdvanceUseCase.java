package net.focik.hr.employee.domain.loan.port.primary;

import net.focik.hr.employee.domain.loan.Loan;

public interface AddAdvanceUseCase {
    Integer addAdvance(Loan advance);
}
