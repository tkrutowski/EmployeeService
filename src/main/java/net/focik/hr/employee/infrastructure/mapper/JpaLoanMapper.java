package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.infrastructure.dto.LoanDbDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDbDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaLoanMapper {

    public LoanDbDto toDto(Loan loan) {
        return LoanDbDto.builder()
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

    public Loan toDomain(LoanDbDto dto, List<LoanInstallmentDbDto> loanInstallments) {
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
    public Loan toDomain(LoanDbDto dto) {
        return Loan.builder()
                .idLoan(dto.getId())
                .idEmployee(dto.getIdEmployee())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .otherInfo(dto.getOtherInfo())
                .installmentAmount(dto.getInstallmentAmount())
                .name(dto.getName())
                .loanStatus(dto.getLoanStatus())
                .build();
    }

    private List<LoanInstallment> mapListLoanInstallmentToSet(List<LoanInstallmentDbDto> installmentDtoList) {
        List<LoanInstallment> loanInstallmentSet = new ArrayList<>();

        installmentDtoList.stream()
                .map(this::toDomain)
                .forEach(loanInstallmentSet::add);

        return loanInstallmentSet;
    }

    public LoanInstallmentDbDto toDto(LoanInstallment loanInstallment) {
        return LoanInstallmentDbDto.builder()
                .id(loanInstallment.getIdLoanInstallment())
                .idLoan(loanInstallment.getIdLoan())
                .amount(loanInstallment.getInstallmentAmount())
                .date(loanInstallment.getDate())
                .isOwnRepayment(loanInstallment.isOwnRepayment())
                .build();
    }

    public LoanInstallment toDomain(LoanInstallmentDbDto dto) {
        return LoanInstallment.builder()
                .idLoanInstallment(dto.getId())
                .idLoan(dto.getIdLoan())
                .installmentAmount(dto.getAmount())
                .date(dto.getDate())
                .isOwnRepayment(dto.getIsOwnRepayment() != null ? dto.getIsOwnRepayment():false)
                .build();
    }
}
