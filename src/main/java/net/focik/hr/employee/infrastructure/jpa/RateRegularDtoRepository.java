package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RateRegularDtoRepository extends JpaRepository<RateRegularDbDto, Integer> {

    List<RateRegularDbDto> findAllByIdEmployee(Integer employeeId);

    void deleteByIdEmployee(Integer idEmployee);

    List<RateRegularDbDto> findByIdEmployeeAndAndDateFromOrderByIdRateDesc(Integer integer, LocalDate date);
}
