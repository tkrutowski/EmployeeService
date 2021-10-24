package net.focik.hr.employee.domain.finances;

import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.finances.port.secondary.AdvanceCommandRepository;
import net.focik.hr.employee.domain.finances.port.secondary.AdvanceQueryRepository;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryAdvanceCommandRepositoryAdapter;
import net.focik.hr.employee.infrastructure.inMemory.InMemoryAdvanceQueryRepositoryAdapter;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SalaryFacadeInMemoryTest {
    AdvanceQueryRepository advanceQueryRepository = new InMemoryAdvanceQueryRepositoryAdapter();
    AdvanceQueryService advanceQueryService = new AdvanceQueryService(advanceQueryRepository);
    SalaryQueryService salaryQueryService = new SalaryQueryService(advanceQueryService);
    SalaryFacade salaryFacade = new SalaryFacade(salaryQueryService);

    AdvanceCommandRepository advanceCommandRepository = new InMemoryAdvanceCommandRepositoryAdapter();
    AdvanceCommandService advanceCommandService = new AdvanceCommandService(advanceCommandRepository);

    @Test
    void should_return_advance_sum_equals_550_when_advance_1_and_2_aded() {
        int i=0;
        //given
        Integer idEmployee = 222;
        LocalDate date = LocalDate.of(2021,10,1);
        advanceCommandService.addAdvance(createAdvance1(),idEmployee);
        advanceCommandService.addAdvance(createAdvance2(),idEmployee);

        //given
        Money sum = salaryFacade.calculateSalary(idEmployee, date).getAdvancesSum();

        //then
        assertEquals(BigDecimal.valueOf(550).doubleValue(), sum.getNumber().doubleValue());
        assertEquals("PLN", sum.getCurrency().toString());
        assertTrue(sum.equals(Money.of(550, "PLN")));
    }

    private Advance createAdvance1(){
        int i =0;
        Advance advance = new Advance(1,BigDecimal.valueOf(300), LocalDate.of(2021,10,24),
                "pierwsza");
        return advance;
    }
    private Advance createAdvance2(){
        int i =0;
        Advance advance = new Advance(2,BigDecimal.valueOf(250), LocalDate.of(2021,10,4),
                "druga");
        return advance;
    }
}