package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.AdditionTypeDbDto;
import org.springframework.data.jpa.repository.JpaRepository;


interface AdditionTypeDtoRepository extends JpaRepository<AdditionTypeDbDto, Integer> {

}
