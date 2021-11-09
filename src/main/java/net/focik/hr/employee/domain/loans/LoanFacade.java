package net.focik.hr.employee.domain.loans;

import lombok.AllArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class LoanFacade {

    LoanService loanService;

    public Integer addLoan(Loan loan, Integer idEmployee){
        return loanService.addLoan(loan, idEmployee);
    }

    public Integer addLoanInstallment(LoanInstallment loanInstallment){
        return loanService.addLoanInstallment(loanInstallment);
    }

    public Money getInstallmentLoansSumByIdEmployeeAndDate(int idEmployee, LocalDate date){
        return loanService.getInstallmentLoansSumByIdEmployeeAndDate(idEmployee, date);
    }
}
