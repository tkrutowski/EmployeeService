package net.focik.hr.employee.domain.advance;

import lombok.AllArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class AdvanceFacade {

    private final AdvanceService advanceService;

    public Integer addAdvance(Advance advance) {
        return advanceService.addAdvance(advance);
    }

    public Money getAdvancesSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        return advanceService.getAdvancesSumByIdEmployeeAndDate(idEmployee, date);
    }


    public List<Advance> getAdvances(Integer idEmployee, LocalDate date) {
        return advanceService.findByEmployeeIdAndDate(idEmployee, date);
    }


    public Integer updateAdvance(Advance advance) {
        return advanceService.updateAdvance(advance);
    }

    public void deleteAdvance(int id) {
        advanceService.deleteAdvance(id);
    }

}
