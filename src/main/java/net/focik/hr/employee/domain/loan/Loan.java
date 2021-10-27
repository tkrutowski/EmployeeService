package net.focik.hr.employee.domain.loan;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.focik.hr.employee.domain.share.LoanStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

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
    private Set<LoanInstallment> loanInstallments;
}
