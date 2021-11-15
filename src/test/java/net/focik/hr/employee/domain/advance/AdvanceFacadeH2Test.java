package net.focik.hr.employee.domain.advance;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdvanceFacadeH2Test {

    int ID_EMPLOYEE = 1;
    LocalDate DATE = LocalDate.of(2020, 11, 1);

    @Autowired
    AdvanceFacade advanceFacade;
    @Test
    void getAdvancesSumByIdEmployeeAndDate() {
        //when
        double sum = 1150d;

        Money advancesSum = advanceFacade.getAdvancesSumByIdEmployeeAndDate(ID_EMPLOYEE, DATE);

        //then
        assertEquals(sum, advancesSum.getNumber().doubleValue());
    }
}