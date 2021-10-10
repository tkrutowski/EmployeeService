package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;


interface EmployeeDtoRepository extends JpaRepository<EmployeeDto, Integer> {

}
