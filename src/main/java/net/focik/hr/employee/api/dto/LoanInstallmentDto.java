package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanInstallmentDto {
    private int idLoanInstallment;
    private int idLoan;
    private String amount;
    private String date;
    private boolean isOwnRepayment;
    private String otherInfo;
}