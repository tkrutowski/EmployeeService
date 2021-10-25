package net.focik.hr.employee.domain.advance;

import lombok.AllArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class AdvanceQueryFacade {

    AdvanceQueryService advanceQueryService;

    public Money getAdvancesSumByIdEmployeeAndDate(int idEmployee, LocalDate date){
        return advanceQueryService.getAdvancesSumByIdEmployeeAndDate(idEmployee, date);
    }
}
