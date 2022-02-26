package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.workTimeRecords.DaysToWork;
import net.focik.hr.employee.domain.workTimeRecords.port.secondary.DaysToWorkRepository;
import net.focik.hr.employee.infrastructure.dto.HolidaysDto;
import net.focik.hr.employee.infrastructure.dto.WorkHoursDto;
import net.focik.hr.employee.infrastructure.mapper.JpaDaysToWorkMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Component
@Primary
@AllArgsConstructor
class DaysToWorkRepositoryAdapter implements DaysToWorkRepository {

    HolidaysDtoRepository holidaysDtoRepository;
    WorkHoursDtoRepository workHoursDtoRepository;
    JpaDaysToWorkMapper mapper;


    @Override
    public Optional<DaysToWork> findByDate(Integer year, Integer month) {
        Optional<WorkHoursDto> byId = workHoursDtoRepository.findByYearMonth(year, month);
        LocalDate afterDate = LocalDate.of(year, month, 1);
        LocalDate beforeDate = LocalDate.of(year, month, Month.of(month).length(afterDate.isLeapYear()));
        List<HolidaysDto> allByDateBetween = holidaysDtoRepository.findAllByDateBetween(afterDate, beforeDate);
        return Optional.of(mapper.toDomain(byId, allByDateBetween));
    }
}
