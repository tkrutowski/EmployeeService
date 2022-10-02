package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.loans.port.secondary.LoanRepository;
import net.focik.hr.employee.infrastructure.dto.LoanDbDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDbDto;
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
    public Integer saveLoan(Loan loan) {
        return loanDtoRepository.save(mapper.toDto(loan)).getId();
    }

    @Override
    public Integer saveLoanInstallment(LoanInstallment loanInstallment) {
        return loanInstallmentDtoRepository.save(mapper.toDto(loanInstallment)).getId();
    }

    @Override
    public Optional<Loan> findLoanById(Integer id) {
        Optional<LoanDbDto> loanById = loanDtoRepository.findById(id);
        if (loanById.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.toDomain(loanById.get()));
    }

    @Override
    public Optional<LoanInstallment> findLoanInstallmentById(Integer id) {
        Optional<LoanInstallmentDbDto> byId = loanInstallmentDtoRepository.findById(id);

        if (byId.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.toDomain(byId.get()));
    }

    @Override
    public List<Loan> findLoanByEmployeeId(Integer idEmployee) {
        return loanDtoRepository.findAllByIdEmployee(idEmployee).stream()
                .map(loanDto -> mapper.toDomain(loanDto))
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findAll() {
        return loanDtoRepository.findAll().stream()
                .map(loanDto -> mapper.toDomain(loanDto))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanInstallment> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        List<LoanDbDto> loansByIdEmployee = loanDtoRepository.findAllByIdEmployee(employeeId);
        List<LoanInstallmentDbDto> loanInstallmentDbDtos = new ArrayList<>();
        String dateFormat = date.getYear() + String.format("-%02d", date.getMonthValue());
        for (LoanDbDto dto : loansByIdEmployee) {
            loanInstallmentDbDtos.addAll(loanInstallmentDtoRepository.findAllByIdLoanAndDate(dto.getId(), dateFormat));
        }

        return loanInstallmentDbDtos.stream()
                .map(l -> mapper.toDomain(l))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanInstallment> findLoanInstallmentByLoanId(Integer loanId) {
        return loanInstallmentDtoRepository.findAllByIdLoan(loanId).stream()
                .map(loanInstallmentDto -> mapper.toDomain(loanInstallmentDto))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLoanById(int idLoan) {
        loanDtoRepository.deleteById(idLoan);
    }

    @Override
    public void deleteLoanInstallmentById(int idLoanInstallment) {
        loanInstallmentDtoRepository.deleteById(idLoanInstallment);
    }

    @Override
    public void deleteLoanInstallmentByIdLoan(int idLoan) {
        loanInstallmentDtoRepository.deleteByIdLoan(idLoan);
    }
}
