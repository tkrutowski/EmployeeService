package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.EmployeeDbDto;
import org.springframework.data.jpa.repository.JpaRepository;


interface EmployeeDtoRepository extends JpaRepository<EmployeeDbDto, Integer> {

}
