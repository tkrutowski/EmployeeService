package net.focik.hr.employee.domain.addition;

import net.focik.hr.employee.domain.addition.port.secondary.AdditionRepository;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryAdditionRepositoryAdapter;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAddition;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdditionFacadeInMemoryTest {
    static AdditionRepository additionRepository = new InMemoryAdditionRepositoryAdapter();
    static AdditionService additionService = new AdditionService(additionRepository, null);
    static AdditionFacade additionFacade = new AdditionFacade(additionService);
    static final Integer ID_EMPLOYEE = 222;
    static final LocalDate CALCULATE_SALARY_DATE = LocalDate.of(2021,10,1);

    @BeforeAll
    static void beforeAll() {
        additionFacade.addAddition(createAddition1());
        additionFacade.addAddition(createAddition2());
        DataBaseAddition.getAdditionTypesHashMap().put(1,"za fakture");
        DataBaseAddition.getAdditionTypesHashMap().put(2,"inne");
    }

    @Test
    void should_return_addition_sum_equals_650_when_addition_1_and_2_added() {
        int i=0;

        //given
        Money sum = additionFacade.getAdditionsSumByIdEmployeeAndDate(ID_EMPLOYEE, CALCULATE_SALARY_DATE);

        //then
        assertEquals(BigDecimal.valueOf(650).doubleValue(), sum.getNumber().doubleValue());
        assertEquals("PLN", sum.getCurrency().toString());
        assertTrue(sum.equals(Money.of(650, "PLN")));
    }

    private static Addition createAddition1(){
        int i =0;
        Addition addition = new Addition(1, ID_EMPLOYEE, BigDecimal.valueOf(300), LocalDate.of(2021,10,24),
                "pierwsza", new AdditionType(1, "za fakture"));
        return addition;
    }
    private static Addition createAddition2(){
        int i =0;
        Addition addition = new Addition(2, ID_EMPLOYEE, BigDecimal.valueOf(350), LocalDate.of(2021,10,4),
                "druga", new AdditionType(2,"inne"));
        return addition;
    }
}