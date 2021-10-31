package net.focik.hr.employee.domain.addition.port.secondary;

import net.focik.hr.employee.domain.addition.Addition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdditionQueryRepository {
    Optional<Addition> findById(Integer id);
    List<Addition> findByEmployeeIdAndDate(Integer employeeId, LocalDate date);

}