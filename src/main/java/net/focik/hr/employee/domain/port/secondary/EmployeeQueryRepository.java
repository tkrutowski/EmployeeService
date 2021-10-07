package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.query.EmployeeQueryDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryRepository {
    Optional<EmployeeQueryDto> findById(Integer id);
    List<EmployeeQueryDto> findAll();

}
