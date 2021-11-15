package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.AdvanceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface AdvanceDtoRepository extends JpaRepository<AdvanceDto, Integer> {

    @Query(value = "select * from zaliczka where id_pracownika = ?1 AND extract(year from data)= ?2  AND extract(month from data) = ?3",
            nativeQuery = true)
    List<AdvanceDto> findAllByIdEmployeeAndDate(Integer idEmployee, Integer year, Integer month);
}
