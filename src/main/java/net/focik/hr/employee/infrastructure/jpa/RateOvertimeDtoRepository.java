package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.RateOvertimeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RateOvertimeDtoRepository extends JpaRepository<RateOvertimeDto, Integer> {

    List<RateOvertimeDto> findAllByIdEmployee(Integer EmployeeId);
}
