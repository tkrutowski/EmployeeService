package net.focik.hr.employee.domain.loan;

import lombok.AllArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class LoanQueryFacade {

    LoanQueryService loanQueryService;

    public Money getInstallmentLoansSumByIdEmployeeAndDate(int idEmployee, LocalDate date){
        return loanQueryService.getInstallmentLoansSumByIdEmployeeAndDate(idEmployee, date);
    }
}
