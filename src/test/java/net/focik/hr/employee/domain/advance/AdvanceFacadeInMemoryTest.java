package net.focik.hr.employee.domain.advance;

import net.focik.hr.employee.domain.advance.port.secondary.AdvanceCommandRepository;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceQueryRepository;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryAdvanceCommandRepositoryAdapter;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryAdvanceQueryRepositoryAdapter;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdvanceFacadeInMemoryTest {
    static AdvanceQueryRepository advanceQueryRepository = new InMemoryAdvanceQueryRepositoryAdapter();
    static AdvanceQueryService advanceQueryService = new AdvanceQueryService(advanceQueryRepository);
    static AdvanceQueryFacade advanceQueryFacade = new AdvanceQueryFacade(advanceQueryService);
    static AdvanceCommandRepository advanceCommandRepository = new InMemoryAdvanceCommandRepositoryAdapter();
    static AdvanceCommandService advanceCommandService = new AdvanceCommandService(advanceCommandRepository);

    static final Integer ID_EMPLOYEE = 222;
    static final LocalDate CALCULATE_SALARY_DATE = LocalDate.of(2021,10,1);

    @BeforeAll
    static void beforeAll() {
        advanceCommandService.addAdvance(createAdvance1(), ID_EMPLOYEE);
        advanceCommandService.addAdvance(createAdvance2(), ID_EMPLOYEE);
    }

    @Test
    void should_return_advance_sum_equals_550_when_advance_1_and_2_aded() {
        int i=0;
        //given




        //given
        Money sum = advanceQueryFacade.getAdvancesSumByIdEmployeeAndDate(ID_EMPLOYEE, CALCULATE_SALARY_DATE);

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