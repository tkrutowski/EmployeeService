package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.HolidaysDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


interface HolidaysDtoRepository extends JpaRepository<HolidaysDto, Integer> {

   // @Query("select h from HolidaysDto h where h.date between  ?1 and  ?2")
    //List<HolidaysDto> findAllByDate(LocalDate after, LocalDate before);
    List<HolidaysDto> findAllByDateBetween(LocalDate after, LocalDate before);
}
