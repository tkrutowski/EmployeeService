package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.DayOffTypeDto;
import net.focik.hr.employee.api.dto.EmployeeDto;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import net.focik.hr.employee.domain.workTimeRecords.share.DayOffType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ApiDayOffMapper {



    public DayOffTypeDto toDto(DayOffType type){

        return new DayOffTypeDto(type.getIdValue(), type.getDbValue());
    }
}
