package net.focik.hr.employee.domain.loans;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanFacadeH2Test {

    @Autowired
    LoanFacade loanFacade;

    int ID_EMPLOYEE = 1;
    LocalDate DATE = LocalDate.of(2020, 11, 1);

    @Test
    void getInstallmentLoansSumByIdEmployeeAndDate() {
        //given
        double sum = 805d;

        //when
        Money sumLoanInstallment = loanFacade.getInstallmentLoansSumByIdEmployeeAndDate(ID_EMPLOYEE, DATE);

        //then
        assertEquals(sum, sumLoanInstallment.getNumber().doubleValue());
    }
}