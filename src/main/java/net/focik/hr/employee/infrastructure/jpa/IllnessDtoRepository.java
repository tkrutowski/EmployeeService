package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.IllnessDto;
import net.focik.hr.employee.infrastructure.dto.IllnessId;
import org.springframework.data.jpa.repository.JpaRepository;


interface IllnessDtoRepository extends JpaRepository<IllnessDto, IllnessId> {

}
