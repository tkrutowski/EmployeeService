package net.focik.hr.employee.domain.addition;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionQueryRepository;
import net.focik.hr.employee.domain.advance.Advance;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
class AdditionQueryService {

    private AdditionQueryRepository additionQueryRepository;

    Money getAdditionsSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        Money sum = Money.of(0, "PLN");
        List<Addition> byIdEmployeeAndDate = additionQueryRepository.findByEmployeeIdAndDate(idEmployee, date);

        if (byIdEmployeeAndDate != null && !byIdEmployeeAndDate.isEmpty()) {
            for (Addition a : byIdEmployeeAndDate) {
                sum = sum.add(Money.of(a.getAmount(), "PLN"));
            }
        }
        return sum;
    }
}
