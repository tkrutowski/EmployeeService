package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.AdditionTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;


interface AdditionTypeDtoRepository extends JpaRepository<AdditionTypeDto, Integer> {

}
