package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class JpaLoanMapper {

    public LoanDto toDto(Loan loan){
        LoanDto loanDto = LoanDto.builder()
                .id(loan.getIdLoan())
                .idEmployee(loan.getIdEmployee())
                .amount(loan.getAmount())
                .date(loan.getDate())
                .otherInfo(loan.getOtherInfo())
                .installmentAmount(loan.getInstallmentAmount())
                .name(loan.getName())
                .loanStatus(loan.getLoanStatus())
                .build();
        return loanDto;
    }

    public Loan toDomain(LoanDto dto, Set<LoanInstallment> loanInstallments){
        Loan loan = Loan.builder()
                .idLoan(dto.getId())
                .idEmployee(dto.getIdEmployee())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .otherInfo(dto.getOtherInfo())
                .installmentAmount(dto.getInstallmentAmount())
                .name(dto.getName())
                .loanStatus(dto.getLoanStatus())
                .loanInstallments(loanInstallments)
                .build();

        return loan;
    }

    public LoanInstallmentDto toDto(LoanInstallment loanInstallment){
        LoanInstallmentDto loanDto = LoanInstallmentDto.builder()
                .id(loanInstallment.getIdLoanInstallment())
                .idLoan(loanInstallment.getIdLoan())
                .amount(loanInstallment.getInstallmentAmount())
                .date(loanInstallment.getDate())
                .isOwnRepayment(loanInstallment.isOwnRepayment())
                .build();
        return loanDto;
    }

    public LoanInstallment toDomain(LoanInstallmentDto dto){
        LoanInstallment loan = LoanInstallment.builder()
                .idLoanInstallment(dto.getId())
                .idLoan(dto.getIdLoan())
                .installmentAmount(dto.getAmount())
                .date(dto.getDate())
                .isOwnRepayment(dto.getIsOwnRepayment())
                .build();

        return loan;
    }
}
