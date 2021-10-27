package net.focik.hr.employee.domain.loan;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.AdvanceNotValidException;
import net.focik.hr.employee.domain.loan.port.secondary.LoanCommandRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
class LoanCommandService {

    private LoanCommandRepository loanCommandRepository;

    Integer addLoan(Loan loan, Integer idEmployee) {
        if(!validate(loan))
            throw new AdvanceNotValidException();
        return loanCommandRepository.addLoan(loan);
    }

    private boolean validate(Loan a){
//        boolean isValid = false;

        if( a.getAmount() == BigDecimal.ZERO)
            return false;
        if(a.getDate() == null )
            return false;

        return true;
    }

    public Integer addLoanInstallment(LoanInstallment loanInstallment) {
        return loanCommandRepository.addLoanInstallment(loanInstallment);
    }
}
