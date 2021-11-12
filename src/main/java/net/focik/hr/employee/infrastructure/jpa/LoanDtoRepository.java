package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.LoanDto;
import org.springframework.data.jpa.repository.JpaRepository;


interface LoanDtoRepository extends JpaRepository<LoanDto, Integer> {

}
