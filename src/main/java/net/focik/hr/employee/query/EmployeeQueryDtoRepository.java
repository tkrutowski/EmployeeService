package net.focik.hr.employee.query;

import net.focik.hr.employee.infrastructure.dto.EmployeeDbDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface EmployeeQueryDtoRepository extends JpaRepository<EmployeeDbDto, Integer> {

    @Query(value = "select * from pracownik  where zatrudniony = ?1 order by nazwisko asc",
            nativeQuery = true)
    List<EmployeeDbDto> findAllByEmploymentStatus(String status);
}
