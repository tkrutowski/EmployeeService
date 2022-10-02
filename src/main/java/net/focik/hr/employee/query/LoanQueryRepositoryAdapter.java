package net.focik.hr.employee.query;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.loans.port.secondary.LoanQueryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
@AllArgsConstructor
class LoanQueryRepositoryAdapter implements LoanQueryRepository {

    LoanQueryDtoRepository loanDtoRepository;
    LoanInstallmentQueryDtoRepository loanInstallmentQueryDtoRepository;


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

    @Override
    public List<LoanInstallmentQueryDto> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        List<LoanQueryDbDto> loansByIdEmployee = loanDtoRepository.findAllByIdEmployee(employeeId);
        List<LoanInstallmentQueryDbDto> loanInstallmentDbDtos = new ArrayList<>();
        String dateFormat = date.getYear() + String.format("-%02d", date.getMonthValue());
        for (LoanQueryDbDto dto : loansByIdEmployee) {
            loanInstallmentDbDtos.addAll(loanInstallmentQueryDtoRepository.findAllByIdLoanAndDate(dto.getId(), dateFormat));
        }

        return loanInstallmentDbDtos.stream()
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

    public LoanInstallmentQueryDto toDto(LoanInstallmentQueryDbDto l) {
        return LoanInstallmentQueryDto.builder()
                .idLoan(l.getIdLoan())
                .idLoanInstallment(l.getId())
                .amount(String.format("%.2f", l.getAmount()).replace(",", "."))
                .date(l.getDate().toString())
//                .otherInfo(l.getgetOtherInfo() == null ? "" : l.getOtherInfo())
                .isOwnRepayment(l.getIsOwnRepayment())
                .loanName(l.getLoanName())
                .build();
    }
}
