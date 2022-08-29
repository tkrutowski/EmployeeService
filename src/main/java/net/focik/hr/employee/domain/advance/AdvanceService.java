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

    private final AdvanceRepository advanceRepository;

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

    Integer addAdvance(Advance advance) {
        if (isNotValid(advance))
            throw new AdvanceNotValidException();
        return advanceRepository.addAdvance(advance);
    }


    public List<Advance> findByEmployeeIdAndDate(Integer idEmployee, LocalDate date) {
        return advanceRepository.findByEmployeeIdAndDate(idEmployee, date);
    }

    public Integer updateAdvance(Advance advance) {
        return advanceRepository.updateAdvance(advance);
    }

    public void deleteAdvance(int id) {
        advanceRepository.deleteAdvanceById(id);
    }


    private boolean isNotValid(Advance a) {
        return (a.getAmount().equals(BigDecimal.ZERO) || a.getDate() == null);
    }
}
