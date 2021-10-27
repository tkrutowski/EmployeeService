package net.focik.hr.employee.domain.loan;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoanCommandFacade {

    LoanCommandService loanCommandService;

    public Integer addLoan(Loan loan, Integer idEmployee){
        return loanCommandService.addLoan(loan, idEmployee);
    }

    public Integer addLoanInstallment(LoanInstallment loanInstallment){
        return loanCommandService.addLoanInstallment(loanInstallment);
    }
}
