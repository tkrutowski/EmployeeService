package net.focik.hr.employee.domain.advance.port.secondary;

import net.focik.hr.employee.domain.advance.Advance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdvanceRepository {
    Optional<Advance> findById(Integer id);
    List<Advance> findByEmployeeIdAndDate(Integer employeeId, LocalDate date);
    Integer add(Advance advance, Integer idEmployee);

}
