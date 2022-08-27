package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface AdditionDtoRepository extends JpaRepository<AdditionDto, Integer> {

    @Query(value = "select * from dodatek where id_pracownika = ?1 AND data like ?2% order by data",
            nativeQuery = true)
    List<AdditionDto> findAllByIdEmployeeAndDate(Integer idEmployee, String data);

}
