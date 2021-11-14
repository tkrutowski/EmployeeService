package net.focik.hr.employee.infrastructure.jpa;

import net.focik.hr.employee.infrastructure.dto.HolidaysDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


interface HolidaysDtoRepository extends JpaRepository<HolidaysDto, Integer> {

    List<HolidaysDto> findAllByDateBetween(LocalDate after, LocalDate before);
}
