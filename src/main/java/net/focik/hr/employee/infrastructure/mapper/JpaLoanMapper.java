package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JpaLoanMapper {

    public LoanDto toDto(Loan loan) {
        return LoanDto.builder()
                .id(loan.getIdLoan())
                .idEmployee(loan.getIdEmployee())
                .amount(loan.getAmount())
                .date(loan.getDate())
                .otherInfo(loan.getOtherInfo())
                .installmentAmount(loan.getInstallmentAmount())
                .name(loan.getName())
                .loanStatus(loan.getLoanStatus())
                .build();
    }

    public Loan toDomain(LoanDto dto, List<LoanInstallmentDto> loanInstallments) {
        return Loan.builder()
                .idLoan(dto.getId())
                .idEmployee(dto.getIdEmployee())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .otherInfo(dto.getOtherInfo())
                .installmentAmount(dto.getInstallmentAmount())
                .name(dto.getName())
                .loanStatus(dto.getLoanStatus())
                .loanInstallments(mapListLoanInstallmentToSet(loanInstallments))
                .build();
    }

    private Set<LoanInstallment> mapListLoanInstallmentToSet(List<LoanInstallmentDto> installmentDtoList) {
        Set<LoanInstallment> loanInstallmentSet = new HashSet<>();

        installmentDtoList.stream()
                .map(this::toDomain)
                .forEach(loanInstallmentSet::add);

        return loanInstallmentSet;
    }

    public LoanInstallmentDto toDto(LoanInstallment loanInstallment) {
        return LoanInstallmentDto.builder()
                .id(loanInstallment.getIdLoanInstallment())
                .idLoan(loanInstallment.getIdLoan())
                .amount(loanInstallment.getInstallmentAmount())
                .date(loanInstallment.getDate())
                .isOwnRepayment(loanInstallment.isOwnRepayment())
                .build();
    }

    public LoanInstallment toDomain(LoanInstallmentDto dto) {
        return LoanInstallment.builder()
                .idLoanInstallment(dto.getId())
                .idLoan(dto.getIdLoan())
                .installmentAmount(dto.getAmount())
                .date(dto.getDate())
                .isOwnRepayment(dto.getIsOwnRepayment())
                .build();
    }
}
