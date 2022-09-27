package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.loans.Loan;
import net.focik.hr.employee.domain.loans.LoanFacade;
import net.focik.hr.employee.domain.loans.LoanInstallment;
import net.focik.hr.employee.domain.loans.port.primary.AddLoanUseCase;
import net.focik.hr.employee.domain.loans.port.primary.DeleteLoanUseCase;
import net.focik.hr.employee.domain.loans.port.primary.GetLoanUseCase;
import net.focik.hr.employee.domain.loans.port.primary.UpdateLoanUseCase;
import net.focik.hr.employee.domain.share.LoanStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class LoanApiService implements GetLoanUseCase, AddLoanUseCase, UpdateLoanUseCase, DeleteLoanUseCase {

    private final LoanFacade loanFacade;


    @Override
    public LoanInstallment getLoanInstallment(Integer idLoanInstallment) {
        return null;
    }

    @Override
    public List<Loan> getLoansByEmployee(Integer idEmployee, boolean withInstallment) {
        return loanFacade.findLoansByEmployee(idEmployee, withInstallment);
    }

    @Override
    public List<Loan> getLoansByEmployee(Integer idEmployee, LoanStatus loanStatus, boolean withInstallment) {
        return loanFacade.findLoansByEmployee(idEmployee, loanStatus, withInstallment);
    }

    @Override
    public List<LoanInstallment> getLoanInstallments(Integer idEmployee, LocalDate date) {
        return loanFacade.getLoanInstallments(idEmployee, date);
    }

    @Override
    public List<LoanInstallment> getLoanInstallments(Integer idLoan) {
        return loanFacade.getLoanInstallments(idLoan);
    }

    @Override
    public Loan getLoanById(int idLoan, boolean withInstallment) {
        return loanFacade.findLoanById(idLoan, withInstallment);
    }

    @Override
    public List<Loan> getLoansByStatus(LoanStatus loanStatus, boolean withInstallment) {
        return loanFacade.findLoansByStatus(loanStatus, withInstallment);
    }

    @Override
    public Integer addLoan(Loan loan) {
        return loanFacade.addLoan(loan);
    }

    @Override
    public Integer addLoanInstallment(LoanInstallment loanInstallment) {
        return loanFacade.addLoanInstallment(loanInstallment);
    }

    @Override
    public void updateLoanStatus(int idLoan, LoanStatus loanStatus) {
        loanFacade.updateLoanStatus(idLoan, loanStatus);
    }

    @Override
    public Integer updateLoan(Loan loan) {
        return loanFacade.updateLoan(loan);
    }

    @Override
    public Integer updateLoanInstallment(LoanInstallment loanInstallment) {
        return loanFacade.updateLoanInstallment(loanInstallment);
    }

    @Override
    public void deleteLoanById(int idLoan) {
        loanFacade.deleteLoan(idLoan);
    }

    @Override
    public void deleteLoanInstallmentById(int id) {
        loanFacade.deleteLoanInstallmentById(id);
    }
}
