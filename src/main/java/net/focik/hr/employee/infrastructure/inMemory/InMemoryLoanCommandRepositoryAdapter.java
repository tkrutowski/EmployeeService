package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionCommandRepository;
import net.focik.hr.employee.domain.loan.Loan;
import net.focik.hr.employee.domain.loan.LoanInstallment;
import net.focik.hr.employee.domain.loan.port.secondary.LoanCommandRepository;
import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import net.focik.hr.employee.infrastructure.dto.JpaMapper;
import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.dto.LoanInstallmentDto;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAddition;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseLoans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("inMemory")
@Profile("test")
@Log
public class InMemoryLoanCommandRepositoryAdapter implements LoanCommandRepository {

    JpaMapper jpaMapper=new JpaMapper();

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
}
