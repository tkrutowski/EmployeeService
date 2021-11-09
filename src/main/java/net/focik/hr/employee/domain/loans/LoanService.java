package net.focik.hr.employee.domain.loans;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.AdvanceNotValidException;
import net.focik.hr.employee.domain.loans.port.secondary.LoanRepository;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
class LoanService {

    private LoanRepository loanRepository;

    Integer addLoan(Loan loan, Integer idEmployee) {
        if(!validate(loan))
            throw new AdvanceNotValidException();
        return loanRepository.addLoan(loan);
    }

    private boolean validate(Loan a){
        if( a.getAmount() == BigDecimal.ZERO)
            return false;
        if(a.getDate() == null )
            return false;

        return true;
    }

    public Integer addLoanInstallment(LoanInstallment loanInstallment) {
        return loanRepository.addLoanInstallment(loanInstallment);
    }

    Money getInstallmentLoansSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        Money sum = Money.of(0, "PLN");
        List<LoanInstallment> byIdEmployeeAndDate = loanRepository.findLoanInstallmentByEmployeeIdAndDate(idEmployee, date);

        if (byIdEmployeeAndDate != null && !byIdEmployeeAndDate.isEmpty()) {
            for (LoanInstallment a : byIdEmployeeAndDate) {
                sum = sum.add(Money.of(a.getInstallmentAmount(), "PLN"));
            }
        }
        return sum;
    }
}
