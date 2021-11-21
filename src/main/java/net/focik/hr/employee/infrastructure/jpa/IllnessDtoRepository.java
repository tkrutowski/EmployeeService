package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.IllnessDto;
import net.focik.hr.employee.infrastructure.dto.IllnessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface IllnessDtoRepository extends JpaRepository<IllnessDto, IllnessId> {

    @Query(value = "select * from choroba where id_pracownika = ?1 AND  data like ?2%",
            nativeQuery = true)
    List<IllnessDto> findAllByIdEmployeeAndDate(int idEmployee, String data);
}
