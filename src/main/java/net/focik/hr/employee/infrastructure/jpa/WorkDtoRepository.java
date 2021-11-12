package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.dto.WorkDto;
import net.focik.hr.employee.infrastructure.dto.WorkId;
import org.springframework.data.jpa.repository.JpaRepository;


interface WorkDtoRepository extends JpaRepository<WorkDto, WorkId> {

}
