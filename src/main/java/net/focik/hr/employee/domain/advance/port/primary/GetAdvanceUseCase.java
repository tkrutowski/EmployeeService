package net.focik.hr.employee.domain.advance.port.primary;

import net.focik.hr.employee.domain.advance.Advance;

import java.time.LocalDate;
import java.util.List;

public interface GetAdvanceUseCase {
    Advance getAdvance(Integer id);

    List<Advance> getAdvances(Integer idEmployee, LocalDate date);
}
