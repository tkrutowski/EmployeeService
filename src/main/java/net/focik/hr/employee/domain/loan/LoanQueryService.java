package net.focik.hr.employee.domain.loan;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.loan.port.secondary.LoanQueryRepository;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
class LoanQueryService {

    private LoanQueryRepository loanQueryRepository;

    Money getInstallmentLoansSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        Money sum = Money.of(0, "PLN");
        List<LoanInstallment> byIdEmployeeAndDate = loanQueryRepository.findLoanInstallmentByEmployeeIdAndDate(idEmployee, date);

        if (byIdEmployeeAndDate != null && !byIdEmployeeAndDate.isEmpty()) {
            for (LoanInstallment a : byIdEmployeeAndDate) {
                sum = sum.add(Money.of(a.getInstallmentAmount(), "PLN"));
            }
        }
        return sum;
    }
}
