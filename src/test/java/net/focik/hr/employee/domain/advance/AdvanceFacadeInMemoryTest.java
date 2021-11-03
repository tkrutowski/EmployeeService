package net.focik.hr.employee.domain.advance;

import net.focik.hr.employee.domain.advance.port.secondary.AdvanceRepository;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryAdvanceRepositoryAdapter;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdvanceFacadeInMemoryTest {
    static AdvanceRepository advanceRepository = new InMemoryAdvanceRepositoryAdapter();
    static AdvanceService advanceService = new AdvanceService(advanceRepository);
    static AdvanceFacade advanceFacade = new AdvanceFacade(advanceService);
    static final Integer ID_EMPLOYEE = 222;
    static final LocalDate CALCULATE_SALARY_DATE = LocalDate.of(2021,10,1);

    @BeforeAll
    static void beforeAll() {
        advanceFacade.addAdvance(createAdvance1(), ID_EMPLOYEE);
        advanceFacade.addAdvance(createAdvance2(), ID_EMPLOYEE);
    }

    @Test
    void should_return_advance_sum_equals_550_when_advance_1_and_2_aded() {
        int i=0;

        //given
        Money sum = advanceFacade.getAdvancesSumByIdEmployeeAndDate(ID_EMPLOYEE, CALCULATE_SALARY_DATE);

        //then
        assertEquals(BigDecimal.valueOf(550).doubleValue(), sum.getNumber().doubleValue());
        assertEquals("PLN", sum.getCurrency().toString());
        assertTrue(sum.equals(Money.of(550, "PLN")));
    }

    private static Advance createAdvance1(){
        int i =0;
        Advance advance = new Advance(1,BigDecimal.valueOf(300), LocalDate.of(2021,10,24),
                "pierwsza");
        return advance;
    }
    private static Advance createAdvance2(){
        int i =0;
        Advance advance = new Advance(2,BigDecimal.valueOf(250), LocalDate.of(2021,10,4),
                "druga");
        return advance;
    }
}