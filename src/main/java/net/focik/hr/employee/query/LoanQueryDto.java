package net.focik.hr.employee.query;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LoanQueryDto {
    private int idLoan;
    private int idEmployee;
    private String amount;
    private String name;
    private String date;
    private String installmentAmount;
    private String otherInfo;
    private String loanStatus;
    private String employee;
    private String amountToPay;
}
