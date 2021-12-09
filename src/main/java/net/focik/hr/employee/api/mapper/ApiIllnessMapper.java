package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.DayOffTypeDto;
import net.focik.hr.employee.api.dto.IllnessTypeDto;
import net.focik.hr.employee.domain.workTimeRecords.share.DayOffType;
import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;
import org.springframework.stereotype.Component;

@Component
public class ApiIllnessMapper {



    public IllnessTypeDto toDto(IllnessType type){

        return new IllnessTypeDto(type.getIdValue(), type.getDbValue());
    }
}
