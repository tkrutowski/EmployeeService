package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.LoanDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


interface LoanDtoRepository extends JpaRepository<LoanDto, Integer> {

    List<LoanDto> findAllByIdEmployee(Integer idEmployee);
}
