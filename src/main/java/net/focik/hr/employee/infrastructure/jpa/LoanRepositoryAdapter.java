package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceRepository;
import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.loans.port.secondary.LoanRepository;
import net.focik.hr.employee.infrastructure.dto.AdvanceDto;
import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDto;
import net.focik.hr.employee.infrastructure.mapper.JpaAdvanceMapper;
import net.focik.hr.employee.infrastructure.mapper.JpaLoanMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
@AllArgsConstructor
class LoanRepositoryAdapter implements LoanRepository {

    LoanDtoRepository loanDtoRepository;
    LoanInstallmentDtoRepository loanInstallmentDtoRepository;
    JpaLoanMapper mapper;


    @Override
    public Integer addLoan(Loan loan) {
        return loanDtoRepository.save(mapper.toDto(loan)).getId();
    }

    @Override
    public Integer addLoanInstallment(LoanInstallment loanInstallment) {
        return loanInstallmentDtoRepository.save(mapper.toDto(loanInstallment)).getId();
    }

    @Override
    public Optional<Loan> findLoanById(Integer id) {
        Optional<LoanDto> loanById = loanDtoRepository.findById(id);
        if(loanById.isEmpty())
            return Optional.empty();
        List<LoanInstallmentDto> loanInstallmentByIdLoan = loanInstallmentDtoRepository.findAllByIdLoan(id);

        return Optional.of(mapper.toDomain(loanById.get(), loanInstallmentByIdLoan));
    }

    @Override
    public Optional<LoanInstallment> findLoanInstallmentById(Integer id) {
        Optional<LoanInstallmentDto> byId = loanInstallmentDtoRepository.findById(id);

        if(byId.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.toDomain(byId.get()));
    }

    @Override
    public List<LoanInstallment> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        List<LoanDto> loansByIdEmployee = loanDtoRepository.findAllByIdEmployee(employeeId);
        List<LoanInstallmentDto> loanInstallmentDtos = new ArrayList<>();
        for (LoanDto dto : loansByIdEmployee ) {
            loanInstallmentDtoRepository.findAllByIdLoanAndDate(dto.getId(), date.getYear(), date.getMonth().getValue()).stream()
                    .forEach(loanInstallmentDto -> loanInstallmentDtos.add(loanInstallmentDto));
        }

        return loanInstallmentDtos.stream()
                .map(l -> mapper.toDomain(l))
                .collect(Collectors.toList());
    }

}
