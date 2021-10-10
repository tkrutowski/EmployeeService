package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.RateRegularDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RateRegularDtoRepository extends JpaRepository<RateRegularDto, Integer> {

    List<RateRegularDto> findAllByIdEmployee(Integer EmployeeId);
}
