package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.domain.advance.AdvanceFacade;
import net.focik.hr.employee.domain.advance.port.primary.AddAdvanceUseCase;
import net.focik.hr.employee.domain.advance.port.primary.DeleteAdvanceUseCase;
import net.focik.hr.employee.domain.advance.port.primary.GetAdvanceUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AdvanceApiService implements GetAdvanceUseCase, AddAdvanceUseCase, DeleteAdvanceUseCase {

    private final AdvanceFacade advanceFacade;

    @Override
    public Advance getAdvance(Integer id) {
        return null;
    }

    @Override
    public List<Advance> getAdvances(Integer idEmployee, LocalDate date) {
        return advanceFacade.getAdvances(idEmployee, date);
    }

    @Override
    public Integer addAdvance(Advance advance) {
        return advanceFacade.addAdvance(advance);
    }

    @Override
    public Integer updateAdvance(Advance advance) {
        return advanceFacade.updateAdvance(advance);
    }

    @Override
    public void deleteAdvanceById(int id) {
        advanceFacade.deleteAdvance(id);
    }
}