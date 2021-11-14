package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.WorkHoursDto;
import net.focik.hr.employee.infrastructure.dto.WorkHoursId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


interface WorkHoursDtoRepository extends JpaRepository<WorkHoursDto, WorkHoursId> {

    @Query("select h from WorkHoursDto h where h.workHoursId.idYear = ?1 and h.workHoursId.idMonth = ?2")
    Optional<WorkHoursDto> findByYearMonth(Integer year, Integer month);
}
