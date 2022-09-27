package net.focik.hr.employee.domain.loans;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@ToString
public class LoanInstallment {
    private int idLoanInstallment;
    private int idLoan;
    private BigDecimal installmentAmount;
    private LocalDate date;
    private boolean isOwnRepayment;
    private String otherInfo;
}
