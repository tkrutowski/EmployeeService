package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.workTimeRecords.DaysToWork;
import net.focik.hr.employee.infrastructure.dto.HolidaysDto;
import net.focik.hr.employee.infrastructure.dto.WorkHoursDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JpaDaysToWorkMapper {

    public DaysToWork toDomain(Optional<WorkHoursDto> workHoursDto, List<HolidaysDto> holidaysDtos){

        DaysToWork daysToWork = DaysToWork.builder()
                .hoursToWork(workHoursDto.isEmpty() ? 0 : workHoursDto.get().getNumberOfHours())
                .holidays(convertHolidayToMap(holidaysDtos))
                .build();

        return daysToWork;
    }

    private Map<LocalDate, String> convertHolidayToMap(List<HolidaysDto> holidaysDtos) {
        Map<LocalDate, String> holidaysMap = new HashMap<>();
        if(holidaysDtos == null)
            return holidaysMap;

        holidaysDtos.stream()
                .forEach(holidaysDto -> holidaysMap.put(holidaysDto.getDate(), holidaysDto.getDescription()));
        return holidaysMap;
    }

}
