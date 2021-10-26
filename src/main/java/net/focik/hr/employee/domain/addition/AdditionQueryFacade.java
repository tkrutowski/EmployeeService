package net.focik.hr.employee.domain.addition;

import lombok.AllArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class AdditionQueryFacade {

    AdditionQueryService additionQueryService;

    public Money getAdditionsSumByIdEmployeeAndDate(int idEmployee, LocalDate date){
        return additionQueryService.getAdditionsSumByIdEmployeeAndDate(idEmployee, date);
    }
}
