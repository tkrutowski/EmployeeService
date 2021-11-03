package net.focik.hr.employee.domain.advance;

import lombok.AllArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class AdvanceFacade {

    AdvanceService advanceService;

    public Integer addAdvance(Advance advance, Integer idEmployee){
        return advanceService.addAdvance(advance, idEmployee);
    }

    public Money getAdvancesSumByIdEmployeeAndDate(int idEmployee, LocalDate date){
        return advanceService.getAdvancesSumByIdEmployeeAndDate(idEmployee, date);
    }
}
