package net.focik.hr.employee.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class LoanDto {
    private int idLoan;
    private int idEmployee;
    private String amount;
    private String name;
    private String date;
    private String installmentAmount;
    private String otherInfo;
    private String loanStatus;
    private List<LoanInstallmentDto> installmentDtoList;
}