package net.focik.hr.employee.domain.loans.port.secondary;

import net.focik.hr.employee.query.LoanInstallmentQueryDto;
import net.focik.hr.employee.query.LoanQueryDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface LoanQueryRepository {
    List<LoanQueryDto> findLoanByEmployeeId(Integer idEmployee);

    List<LoanQueryDto> findAll();

    List<LoanInstallmentQueryDto> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date);
}
