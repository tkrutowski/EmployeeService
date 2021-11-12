package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.AdvanceDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


interface AdvanceDtoRepository extends JpaRepository<AdvanceDto, Integer> {

    List<AdvanceDto> findAllByIdEmployeeAndDate(Integer idEmployee, LocalDate date);
}
