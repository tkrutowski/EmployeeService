package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.DayOffDto;
import net.focik.hr.employee.infrastructure.dto.DayOffId;
import org.springframework.data.jpa.repository.JpaRepository;


interface DayOffDtoRepository extends JpaRepository<DayOffDto, DayOffId> {

}
