package net.focik.hr.employee.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanInstallmentQueryDto {
    private int idLoanInstallment;
    private int idLoan;
    private String amount;
    private String date;
    private boolean isOwnRepayment;
    private String otherInfo;
    private String loanName;
}