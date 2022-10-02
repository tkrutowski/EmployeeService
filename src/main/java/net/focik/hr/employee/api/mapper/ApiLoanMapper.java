package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.LoanDto;
import net.focik.hr.employee.api.dto.LoanInstallmentDto;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.exceptions.LoanNotValidException;
import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.share.LoanStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ApiLoanMapper {

    public Loan toDomain(LoanDto dto) {
        valid(dto);
        return Loan.builder()
                .idLoan(dto.getIdLoan())
                .idEmployee(dto.getIdEmployee())
                .name(dto.getName())
                .date(LocalDate.parse(dto.getDate()))
                .amount(BigDecimal.valueOf(Double.parseDouble(dto.getAmount())))
                .installmentAmount(BigDecimal.valueOf(Double.parseDouble(dto.getInstallmentAmount())))
                .loanStatus(LoanStatus.valueOf(dto.getLoanStatus()))
                .otherInfo(dto.getOtherInfo())
                .build();
    }

    public LoanDto toDto(Loan l) {
        return LoanDto.builder()
                .idLoan(l.getIdLoan())
                .idEmployee(l.getIdEmployee())
                .name(l.getName())
                .installmentAmount(String.format("%.2f", l.getInstallmentAmount()).replace(",", "."))
                .amount(String.format("%.2f", l.getAmount()).replace(",", "."))
                .date(l.getDate().toString())
                .otherInfo(l.getOtherInfo())
                .loanStatus(l.getLoanStatus().name())
                .installmentDtoList(l.getLoanInstallments() != null ? l.getLoanInstallments()
                        .stream().map(this::toDto).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }

    public LoanInstallmentDto toDto(LoanInstallment l) {
        return LoanInstallmentDto.builder()
                .idLoan(l.getIdLoan())
                .idLoanInstallment(l.getIdLoanInstallment())
                .amount(String.format("%.2f", l.getInstallmentAmount()).replace(",", "."))
                .date(l.getDate().toString())
                .otherInfo(l.getOtherInfo() == null ? "" : l.getOtherInfo())
                .isOwnRepayment(l.isOwnRepayment())
                .build();
    }

    public LoanInstallment toDomain(LoanInstallmentDto dto) {
        valid(dto);
        return LoanInstallment.builder()
                .idLoan(dto.getIdLoan())
                .idLoanInstallment(dto.getIdLoanInstallment())
                .date(LocalDate.parse(dto.getDate()))
                .installmentAmount(BigDecimal.valueOf(Double.parseDouble(dto.getAmount())))
                .otherInfo(dto.getOtherInfo())
                .isOwnRepayment(dto.isOwnRepayment())
                .build();
    }

    private void valid(LoanDto dto) {
        if (dto.getIdEmployee() == 0)
            throw new EmployeeNotValidException("IdEmployee can't be null.");
        if (dto.getDate().isEmpty())
            throw new LoanNotValidException("Date can't be empty.");
    }

    private void valid(LoanInstallmentDto dto) {
        if (dto.getIdLoan() == 0)
            throw new LoanNotValidException("IdLoan can't be null.");
        if (dto.getDate().isEmpty())
            throw new LoanNotValidException("Date can't be empty.");
    }
}