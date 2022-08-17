package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RateRegularDtoRepository extends JpaRepository<RateRegularDbDto, Integer> {

    List<RateRegularDbDto> findAllByIdEmployee(Integer EmployeeId);
    void deleteByIdEmployee(Integer idEmployee);
}
