package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.DayOffTypeDto;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;
import org.springframework.stereotype.Component;

@Component
public class ApiDayOffMapper {


    public DayOffTypeDto toDto(DayOffType type) {

        return new DayOffTypeDto(type.getIdValue(), type.getDbValue());
    }
}
