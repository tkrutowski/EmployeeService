package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.IllnessDto;
import net.focik.hr.employee.infrastructure.dto.LoanDto;
import net.focik.hr.employee.infrastructure.dto.WorkDto;
import net.focik.hr.employee.infrastructure.dto.WorkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface WorkDtoRepository extends JpaRepository<WorkDto, WorkId> {

    @Query(value = "select * from praca where id_pracownika = ?1 AND  data like ?2%",
            nativeQuery = true)
    List<WorkDto> findAllByIdEmployeeAndDate(int idEmployee, String data);
}
