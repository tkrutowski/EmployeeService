package net.focik.hr.employee.domain.advance;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceRepository;
import net.focik.hr.employee.domain.exceptions.AdvanceNotValidException;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
class AdvanceService {

    private AdvanceRepository advanceRepository;

    Money getAdvancesSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        Money sum = Money.of(0, "PLN");
        List<Advance> byIdEmployeeAndDate = advanceRepository.findByEmployeeIdAndDate(idEmployee, date);

        if (byIdEmployeeAndDate != null && !byIdEmployeeAndDate.isEmpty()) {
            for (Advance a : byIdEmployeeAndDate) {
                sum = sum.add(Money.of(a.getAmount(), "PLN"));
            }
        }
        return sum;
    }
    Integer addAdvance(Advance advance, Integer idEmployee) {
        if(!validate(advance))
            throw new AdvanceNotValidException();
        return advanceRepository.add(advance, idEmployee);
    }

    private boolean validate(Advance a){
//        boolean isValid = false;

        if( a.getAmount() == BigDecimal.ZERO)
            return false;
        if(a.getDate() == null )
            return false;

        return true;
    }
}
