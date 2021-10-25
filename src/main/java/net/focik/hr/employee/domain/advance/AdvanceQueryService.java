package net.focik.hr.employee.domain.advance;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceQueryRepository;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
class AdvanceQueryService {

    private AdvanceQueryRepository advanceQueryRepository;

    Money getAdvancesSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        Money sum = Money.of(0, "PLN");
        List<Advance> byIdEmployeeAndDate = advanceQueryRepository.findByEmployeeIdAndDate(idEmployee, date);

        if (byIdEmployeeAndDate != null && !byIdEmployeeAndDate.isEmpty()) {
            for (Advance a : byIdEmployeeAndDate) {
                sum = sum.add(Money.of(a.getAmount(), "PLN"));
            }
        }
        return sum;
    }
}
