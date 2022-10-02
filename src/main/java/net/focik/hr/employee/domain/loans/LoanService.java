package net.focik.hr.employee.domain.loans;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.LoanNotFoundException;
import net.focik.hr.employee.domain.exceptions.LoanNotValidException;
import net.focik.hr.employee.domain.loans.port.secondary.LoanRepository;
import net.focik.hr.employee.domain.share.LoanStatus;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.AmountFormatParams;
import org.springframework.stereotype.Service;

import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class LoanService {

    private final LoanRepository loanRepository;

    Integer saveLoan(Loan loan) {
        if (isNotValid(loan))
            throw new LoanNotValidException();
        return loanRepository.saveLoan(loan);
    }

    public Integer addLoanInstallment(LoanInstallment loanInstallment) {
        return loanRepository.saveLoanInstallment(loanInstallment);
    }

    Money getInstallmentLoansSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        Money sum = Money.of(0, "PLN");
        List<LoanInstallment> byIdEmployeeAndDate = loanRepository.findLoanInstallmentByEmployeeIdAndDate(idEmployee, date);

        if (byIdEmployeeAndDate != null && !byIdEmployeeAndDate.isEmpty()) {
            for (LoanInstallment a : byIdEmployeeAndDate) {
                sum = sum.add(Money.of(a.getInstallmentAmount(), "PLN"));
            }
        }
        return sum;
    }

    List<Loan> findLoansByEmployee(int idEmployee, LoanStatus loanStatus, boolean withLoanInstallment) {
        List<Loan> loanByEmployeeId = loanRepository.findLoanByEmployeeId(idEmployee);

        if (withLoanInstallment) {
            for (Loan l : loanByEmployeeId) {
                List<LoanInstallment> loanInstallmentList = findLoanInstallmentByLoanId(l.getIdLoan());
                l.addLoanInstallment(loanInstallmentList);
            }
        }

        if (loanStatus == null)
            return loanByEmployeeId;

        loanByEmployeeId = loanByEmployeeId.stream()
                .filter(loan -> loan.getLoanStatus().equals(loanStatus))
                .collect(Collectors.toList());


        return loanByEmployeeId;
    }

    List<LoanInstallment> findLoanInstallmentByLoanId(int idLoan) {
        return loanRepository.findLoanInstallmentByLoanId(idLoan);
    }

    List<LoanInstallment> getLoanInstallments(Integer idEmployee, LocalDate date) {
        List<Loan> loansByEmployee = findLoansByEmployee(idEmployee, null, true);
        return loansByEmployee.stream()
                .map(Loan::getLoanInstallments)
                .flatMap(Collection::stream)
                .filter(loanInstallment -> loanInstallment.getDate().getYear() == (date.getYear()))
                .filter(loanInstallment -> loanInstallment.getDate().getMonth().equals(date.getMonth()))
                .collect(Collectors.toList());
    }

    Loan findLoanById(int idLoan, boolean withLoanInstallment) {
        Optional<Loan> loanById = loanRepository.findLoanById(idLoan);

        if (loanById.isEmpty()) {
            throw new LoanNotFoundException(idLoan);
        }

        if (withLoanInstallment) {
            List<LoanInstallment> loanInstallmentList = findLoanInstallmentByLoanId(loanById.get().getIdLoan());
            loanById.get().addLoanInstallment(loanInstallmentList);
        }

        return loanById.get();
    }

    List<Loan> findLoansByStatus(LoanStatus loanStatus, boolean withInstallment) {
        List<Loan> loans = loanRepository.findAll();

        if (withInstallment) {
            for (Loan l : loans) {
                List<LoanInstallment> loanInstallmentList = findLoanInstallmentByLoanId(l.getIdLoan());
                l.addLoanInstallment(loanInstallmentList);
            }
        }

        if (loanStatus == null || LoanStatus.ALL.equals(loanStatus))
            return loans;

        loans = loans.stream()
                .filter(loan -> loan.getLoanStatus().equals(loanStatus))
                .collect(Collectors.toList());

        return loans;
    }

    @Transactional
    public void deleteLoan(int idLoan) {
        loanRepository.deleteLoanInstallmentByIdLoan(idLoan);
        loanRepository.deleteLoanById(idLoan);
    }

    public void updateLoan(Loan loan) {
        if (isNotValid(loan))
            throw new LoanNotValidException();
        loanRepository.saveLoan(loan);
    }

    public Integer updateLoanInstallment(LoanInstallment loanInstallment) {
        if (isNotValid(loanInstallment))
            throw new LoanNotValidException();
        return loanRepository.saveLoanInstallment(loanInstallment);
    }

    public void deleteLoanInstallment(int id) {
        loanRepository.deleteLoanInstallmentById(id);
    }

    private boolean isNotValid(Loan a) {
        if (Objects.equals(a.getAmount(), BigDecimal.ZERO))
            return true;
        if (Objects.equals(a.getInstallmentAmount(), BigDecimal.ZERO))
            return true;
        return a.getDate() == null;
    }

    private boolean isNotValid(LoanInstallment loanInstallment) {
        if (Objects.equals(loanInstallment.getInstallmentAmount(), BigDecimal.ZERO))
            return true;
        return loanInstallment.getDate() == null;
    }

    public Money getLoansToPaySum(Integer employeeId) {
        List<Loan> loans = findLoansByEmployee(employeeId, LoanStatus.TO_PAY, true);
        Money result = Money.of(BigDecimal.ZERO, "PLN");

        for (Loan loan : loans) {
            Money loanAmount = mapToMoney(loan.getAmount());
            BigDecimal reduce = loan.getLoanInstallments().stream()
                    .map(LoanInstallment::getInstallmentAmount)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
            Money loanInstallmentPayedOff = Money.of(reduce, "PLN");
            result = result.add(loanAmount.subtract(loanInstallmentPayedOff));
        }

        return result;
    }

    private Money mapToMoney(Number amount ) {
        return Money.of(amount, "PLN");
    }
}
