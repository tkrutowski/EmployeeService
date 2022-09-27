package net.focik.hr.employee.query;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.loans.port.secondary.LoanQueryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
@AllArgsConstructor
class LoanQueryRepositoryAdapter implements LoanQueryRepository {

    LoanQueryDtoRepository loanDtoRepository;

    @Override
    public List<LoanQueryDto> findLoanByEmployeeId(Integer idEmployee) {
        return loanDtoRepository.findAllByIdEmployee(idEmployee).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanQueryDto> findAll() {
        return loanDtoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private LoanQueryDto toDto(LoanQueryDbDto l) {
        return LoanQueryDto.builder()
                .idLoan(l.getId())
                .idEmployee(l.getIdEmployee())
                .name(l.getName())
                .installmentAmount(String.format("%.2f", l.getInstallmentAmount()))
                .amount(String.format("%.2f", l.getAmount()))
                .date(l.getDate().toString())
                .otherInfo(l.getOtherInfo())
                .loanStatus(l.getLoanStatus().name())
                .employee(l.getEmployee())
                .amountToPay(l.getAmountLeft() == null ? String.format("%.2f", l.getAmount()) : String.format("%.2f", l.getAmountLeft()))
                .build();
    }
}
