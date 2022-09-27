package net.focik.hr.employee.domain.loans;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.focik.hr.employee.domain.share.LoanStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@ToString
public class Loan {
    private int idLoan;
    private int idEmployee;
    private BigDecimal amount;
    private String name;
    private LocalDate date;
    private BigDecimal installmentAmount;
    private String otherInfo;
    private LoanStatus loanStatus;
    private List<LoanInstallment> loanInstallments;

    public void addLoanInstallment(List<LoanInstallment> loanInstallments) {
        this.loanInstallments = loanInstallments;
    }

    public void changeLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }
}
