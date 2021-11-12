package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


interface AdditionDtoRepository extends JpaRepository<AdditionDto, Integer> {

    List<AdditionDto> findAllByIdEmployeeAndDate(Integer idEmployee, LocalDate date);
}
