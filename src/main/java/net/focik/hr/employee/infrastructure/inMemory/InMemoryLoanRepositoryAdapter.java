package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.loans.port.secondary.LoanRepository;
import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDto;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseLoans;
import net.focik.hr.employee.infrastructure.mapper.JpaLoanMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Qualifier("inMemory")
@Profile("test")
@Log
public class InMemoryLoanRepositoryAdapter implements LoanRepository {

    JpaLoanMapper jpaMapper=new JpaLoanMapper();

    @Override
    public Integer addLoan(Loan loan) {
        LoanDto loanDto = jpaMapper.toDto(loan);
        log.info("Try add into inMemoryDb loan: " + loanDto.toString());
        if (loanDto == null)
            throw new NullPointerException("Loan cannot be null");
        Integer id = DataBaseLoans.getLoansHashMap().size() + 1;
        // employee.setId(id);
        DataBaseLoans.getLoansHashMap().put(id, loanDto);
        log.info("Succssec id = " + id);
        return id;
    }

    @Override
    public Integer addLoanInstallment(LoanInstallment loanInstallment) {
        LoanInstallmentDto loanInstallmentDto = jpaMapper.toDto(loanInstallment);
        log.info("Try add into inMemoryDb loan installment: " + loanInstallmentDto.toString());
        if (loanInstallmentDto == null)
            throw new NullPointerException("Loan installment cannot be null");
        Integer id = DataBaseLoans.getLoanInstallmentTypesHashMap().size() + 1;
        // employee.setId(id);
        DataBaseLoans.getLoanInstallmentTypesHashMap().put(id, loanInstallmentDto);
        log.info("Succssec id = " + id);
        return id;
    }

    @Override
    public Optional<Loan> findLoanById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<LoanInstallment> findLoanInstallmentById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<LoanInstallment> findLoanInstallmentByEmployeeIdAndDate(Integer employeeId, LocalDate date) {

        List<LoanInstallment> loanInstallments = new ArrayList<>();

        List<LoanDto> loanDtosByIdEmployee = DataBaseLoans.getLoansHashMap()
                .entrySet()
                .stream()
                .map(dtoEntry -> dtoEntry.getValue())
                .filter(loanDto -> loanDto.getIdEmployee().equals(employeeId))
                .collect(Collectors.toList());

        for (LoanDto loanDto : loanDtosByIdEmployee) {
            DataBaseLoans.getLoanInstallmentTypesHashMap().entrySet().stream()
                    .filter(e -> loanDto.getId().equals(e.getValue().getIdLoan()))
                    .map(Map.Entry::getValue)
                    .filter(loanInstallmentDto -> loanInstallmentDto.getDate().getYear() == date.getYear())
                    .filter(loanInstallmentDto -> loanInstallmentDto.getDate().getMonth() == date.getMonth())
                    .map(loanInstallmentDto -> jpaMapper.toDomain(loanInstallmentDto))
                    .forEach(loanInstallment -> loanInstallments.add(loanInstallment));
        }
        return loanInstallments;
    }
}
