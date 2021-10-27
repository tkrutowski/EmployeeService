package net.focik.hr.employee.domain.loan;

import net.focik.hr.employee.domain.loan.port.secondary.LoanCommandRepository;
import net.focik.hr.employee.domain.loan.port.secondary.LoanQueryRepository;
import net.focik.hr.employee.domain.share.LoanStatus;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryAdditionCommandRepositoryAdapter;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryLoanCommandRepositoryAdapter;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryLoanQueryRepositoryAdapter;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAddition;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanCommandFacadeTest {

    static LoanQueryRepository loanQueryRepository = new InMemoryLoanQueryRepositoryAdapter();
    static LoanQueryService loanQueryService = new LoanQueryService(loanQueryRepository);
    static LoanQueryFacade loanQueryFacade = new LoanQueryFacade(loanQueryService);

    static LoanCommandRepository loanCommandRepository = new InMemoryLoanCommandRepositoryAdapter();
    static LoanCommandService loanCommandService = new LoanCommandService(loanCommandRepository);
    static LoanCommandFacade loanCommandFacade = new LoanCommandFacade(loanCommandService);
    static final Integer ID_EMPLOYEE = 222;
    static final LocalDate CALCULATE_SALARY_DATE = LocalDate.of(2021,10,1);
    @BeforeAll
    static void beforeAll() {
        loanCommandFacade.addLoan(createLoan1(), ID_EMPLOYEE);
        loanCommandFacade.addLoan(createLoan2(), 1);
        loanCommandFacade.addLoanInstallment(createLoanInstallment1());
        loanCommandFacade.addLoanInstallment(createLoanInstallment2());
        loanCommandFacade.addLoanInstallment(createLoanInstallment3());
        loanCommandFacade.addLoanInstallment(createLoanInstallment4());
    }

    @Test
    void should_return_addition_sum_equals_650_when_addition_1_and_2_added() {
        int i=0;

        //given
        Money sum = loanQueryFacade.getInstallmentLoansSumByIdEmployeeAndDate(ID_EMPLOYEE, CALCULATE_SALARY_DATE);

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