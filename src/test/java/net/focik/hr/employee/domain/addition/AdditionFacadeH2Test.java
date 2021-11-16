package net.focik.hr.employee.domain.addition;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdditionFacadeH2Test {

    int ID_EMPLOYEE = 1;
    LocalDate DATE = LocalDate.of(2020, 11, 1);

    @Autowired
    AdditionFacade additionFacade;
    @Test
    void getAdditionSumByIdEmployeeAndDate() {
        //when
        double sum = 1150d;

        Money additionSum = additionFacade.getAdditionsSumByIdEmployeeAndDate(ID_EMPLOYEE, DATE);

        //then
        assertEquals(sum, additionSum.getNumber().doubleValue());
    }
}