package net.focik.hr.employee.domain.addition;

import lombok.AllArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class AdditionFacade {

    AdditionService additionService;

    public Money getAdditionsSumByIdEmployeeAndDate(int idEmployee, LocalDate date){
        return additionService.getAdditionsSumByIdEmployeeAndDate(idEmployee, date);
    }

    public Integer addAddition(Addition addition){
        return additionService.addAddition(addition);
    }
}
