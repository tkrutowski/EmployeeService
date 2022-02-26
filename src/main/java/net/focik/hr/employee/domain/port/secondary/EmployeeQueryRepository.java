package net.focik.hr.employee.domain.port.secondary;

import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryRepository {
    Optional<EmployeeQueryBasicDto> findById(Integer id);
    List<EmployeeQueryBasicDto> findAll();
    List<EmployeeQueryBasicDto> findAllByEmploymentStatus(EmploymentStatus employmentStatus);

}
