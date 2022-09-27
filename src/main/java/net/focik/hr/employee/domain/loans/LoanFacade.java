package net.focik.hr.employee.domain.loans;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.share.LoanStatus;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class LoanFacade {

    LoanService loanService;

    public Integer addLoan(Loan loan) {
        return loanService.saveLoan(loan);
    }

    public Integer addLoanInstallment(LoanInstallment loanInstallment) {
        return loanService.addLoanInstallment(loanInstallment);
    }

    public Money getInstallmentLoansSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        return loanService.getInstallmentLoansSumByIdEmployeeAndDate(idEmployee, date);
    }

    public List<Loan> findLoansByEmployee(int idEmployee, LoanStatus loanStatus, boolean withInstallment) {
        return loanService.findLoansByEmployee(idEmployee, loanStatus, withInstallment);
    }


    public List<Loan> findLoansByEmployee(int idEmployee, boolean withInstallment) {
        return loanService.findLoansByEmployee(idEmployee, null, withInstallment);
    }

    public List<LoanInstallment> getLoanInstallments(Integer idEmployee, LocalDate date) {
        return loanService.getLoanInstallments(idEmployee, date);
    }

    public Loan findLoanById(int idLoan, boolean withInstallment) {
        return loanService.findLoanById(idLoan, withInstallment);
    }

    public List<Loan> findLoansByStatus(LoanStatus loanStatus, boolean withInstallment) {
        return loanService.findLoansByStatus(loanStatus, withInstallment);
    }

    public List<LoanInstallment> getLoanInstallments(Integer idLoan) {
        return loanService.findLoanById(idLoan, true).getLoanInstallments();
    }

    public Integer updateLoan(Loan loan) {
        return loanService.saveLoan(loan);
    }

    public void deleteLoan(int idLoan) {
        loanService.deleteLoan(idLoan);
    }

    public void updateLoanStatus(int idLoan, LoanStatus loanStatus) {
        Loan loan = loanService.findLoanById(idLoan, false);
        loan.changeLoanStatus(loanStatus);

        loanService.updateLoan(loan);
    }

    public void deleteLoanInstallmentById(int id) {
        loanService.deleteLoanInstallment(id);
    }

    public Integer updateLoanInstallment(LoanInstallment loanInstallment) {
        return loanService.updateLoanInstallment(loanInstallment);
    }
}
