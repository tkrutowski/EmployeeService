package net.focik.hr.employee.domain.addition;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionRepository;
import net.focik.hr.employee.domain.exceptions.AdvanceNotValidException;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
class AdditionService {

    private AdditionRepository additionRepository;

    Money getAdditionsSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        Money sum = Money.of(0, "PLN");
        List<Addition> byIdEmployeeAndDate = additionRepository.findByEmployeeIdAndDate(idEmployee, date);

        if (byIdEmployeeAndDate != null && !byIdEmployeeAndDate.isEmpty()) {
            for (Addition a : byIdEmployeeAndDate) {
                sum = sum.add(Money.of(a.getAmount(), "PLN"));
            }
        }
        return sum;
    }

    Integer addAddition(Addition addition, Integer idEmployee) {
        if(!validate(addition))
            throw new AdvanceNotValidException();
        return additionRepository.add(addition, idEmployee);
    }

    private boolean validate(Addition a){

        if( a.getAmount() == BigDecimal.ZERO)
            return false;
        if(a.getDate() == null )
            return false;
        if(a.getAdditionType() == null )
            return false;

        return true;
    }
}
