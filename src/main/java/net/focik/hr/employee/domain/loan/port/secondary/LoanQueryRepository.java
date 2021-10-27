package net.focik.hr.employee.domain.loan.port.secondary;

import net.focik.hr.employee.domain.loan.Loan;
import net.focik.hr.employee.domain.loan.LoanInstallment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanQueryRepository {
    Optional<Loan> findById(Integer id);
    List<LoanInstallment> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date);

}
