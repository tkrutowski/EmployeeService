package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.DayOffDto;
import net.focik.hr.employee.infrastructure.dto.DayOffId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface DayOffDtoRepository extends JpaRepository<DayOffDto, DayOffId> {

    @Query(value = "select * from urlop where id_pracownika = ?1 AND  data like ?2%",
            nativeQuery = true)
    List<DayOffDto> findAllByIdEmployeeAndDate(int idEmployee, String data);

}
