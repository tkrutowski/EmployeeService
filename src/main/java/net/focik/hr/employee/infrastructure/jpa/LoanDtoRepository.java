package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.LoanDbDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


interface LoanDtoRepository extends JpaRepository<LoanDbDto, Integer> {

    List<LoanDbDto> findAllByIdEmployee(Integer idEmployee);
}
