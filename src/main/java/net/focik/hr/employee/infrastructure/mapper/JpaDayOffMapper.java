package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.workTimeRecords.DayOff;
import net.focik.hr.employee.infrastructure.dto.DayOffDto;
import net.focik.hr.employee.infrastructure.dto.DayOffId;
import org.springframework.stereotype.Component;

@Component
public class JpaDayOffMapper {

    public DayOffDto toDto(DayOff dayOff){
        DayOffDto dayOffDto = DayOffDto.builder()
                .dayOffId(new DayOffId(dayOff.getIdEmployee(), dayOff.getDate()))
                .dayOffType(dayOff.getDayOffType())
                .build();
        return dayOffDto;
    }

    public DayOff toDomain(DayOffDto dto){
        DayOff dayOff = new DayOff(dto.getDayOffId().getIdEmployee(),dto.getDayOffId().getDate(),dto.getDayOffType());

        return dayOff;
    }
}
