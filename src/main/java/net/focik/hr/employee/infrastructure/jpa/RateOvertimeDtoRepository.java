package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.RateOvertimeDbDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateOvertimeDtoRepository extends JpaRepository<RateOvertimeDbDto, Integer> {

    List<RateOvertimeDbDto> findAllByIdEmployee(Integer idEmployee);

    void deleteByIdEmployee(Integer idEmployee);
}
