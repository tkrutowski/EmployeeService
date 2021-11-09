package net.focik.hr.employee.domain.loans;

import net.focik.hr.employee.domain.loans.port.secondary.LoanRepository;
import net.focik.hr.employee.domain.share.LoanStatus;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryLoanRepositoryAdapter;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanFacadeTest {


    static LoanRepository loanRepository = new InMemoryLoanRepositoryAdapter();
    static LoanService loanService = new LoanService(loanRepository);
    static LoanFacade loanFacade = new LoanFacade(loanService);
    static final Integer ID_EMPLOYEE = 222;
    static final LocalDate CALCULATE_SALARY_DATE = LocalDate.of(2021,10,1);
    @BeforeAll
    static void beforeAll() {
        loanFacade.addLoan(createLoan1(), ID_EMPLOYEE);
        loanFacade.addLoan(createLoan2(), 1);
        loanFacade.addLoanInstallment(createLoanInstallment1());
        loanFacade.addLoanInstallment(createLoanInstallment2());
        loanFacade.addLoanInstallment(createLoanInstallment3());
        loanFacade.addLoanInstallment(createLoanInstallment4());
    }

    @Test
    void should_return_addition_sum_equals_650_when_addition_1_and_2_added() {
        int i=0;

        //given
        Money sum = loanFacade.getInstallmentLoansSumByIdEmployeeAndDate(ID_EMPLOYEE, CALCULATE_SALARY_DATE);

        //then
        assertEquals(BigDecimal.valueOf(800).doubleValue(), sum.getNumber().doubleValue());
        assertEquals("PLN", sum.getCurrency().toString());
        assertTrue(sum.equals(Money.of(800, "PLN")));
    }

    private static Loan createLoan1(){
        int i =0;
        Loan loan = new Loan(1,ID_EMPLOYEE, BigDecimal.valueOf(3000), "motocykl",
                LocalDate.of(2021,8,24), BigDecimal.valueOf(500),
                "pierwsza", LoanStatus.TO_PAY, null);
        return loan;
    }
    private static Loan createLoan2(){
        Loan loan = new Loan(2,ID_EMPLOYEE, BigDecimal.valueOf(45000), "motocykl2",
                LocalDate.of(2021,7,24), BigDecimal.valueOf(450),
                "druga", LoanStatus.TO_PAY, null);
        return loan;
    }
    private static Loan createLoan3(){
        Loan loan = new Loan(3,1, BigDecimal.valueOf(45000), "motocykl2",
                LocalDate.of(2021,7,24), BigDecimal.valueOf(450),
                "druga", LoanStatus.TO_PAY, null);
        return loan;
    }

    private static LoanInstallment createLoanInstallment1(){
        LoanInstallment loanInstallment = new LoanInstallment(1,1, BigDecimal.valueOf(450),
                LocalDate.of(2021,7,24), false );
        return loanInstallment;
    }

    private static LoanInstallment createLoanInstallment2(){
        LoanInstallment loanInstallment = new LoanInstallment(2,1, BigDecimal.valueOf(500),
                LocalDate.of(2021,10,24), false );
        return loanInstallment;
    }

    private static LoanInstallment createLoanInstallment3(){
        LoanInstallment loanInstallment = new LoanInstallment(3,2, BigDecimal.valueOf(500),
                LocalDate.of(2021,7,24), false );
        return loanInstallment;
    }

    private static LoanInstallment createLoanInstallment4(){
        LoanInstallment loanInstallment = new LoanInstallment(4,2, BigDecimal.valueOf(300),
                LocalDate.of(2021,10,4), false );
        return loanInstallment;
    }
}