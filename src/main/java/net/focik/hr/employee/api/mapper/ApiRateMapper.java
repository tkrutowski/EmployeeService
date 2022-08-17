package net.focik.hr.employee.api.mapper;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import net.focik.hr.employee.api.dto.EmployeeDto;
import net.focik.hr.employee.api.dto.RateOvertimeDto;
import net.focik.hr.employee.api.dto.RateRegularDto;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Component
public class ApiRateMapper {

    public RateRegular toDomain (RateRegularDto dto){
        RateRegular build = RateRegular.builder()
                .idRate(dto.getIdRate())
                .rateValue(BigDecimal.valueOf(Double.parseDouble(dto.getRateValue().replace(",","."))))
                .dateFrom(LocalDate.parse(dto.getDateFrom()))
                .rateType(RateType.valueOf(dto.getRateType()))
                .build();
        return build;
    }

    public RateRegularDto toDto(RateRegular rate){
        RateRegularDto build = RateRegularDto.builder()
                .idRate(rate.getIdRate())
                .rateValue(rate.getRateValue().toString())
                .rateType(rate.getRateType().toString())
                .dateFrom(rate.getDateFrom().toString())
                .build();
        return build;
    }

    public RateOvertime toDomain (RateOvertimeDto dto){
        RateOvertime build = RateOvertime.builder()
                .idRate(dto.getIdRate())
                .rateValue(BigDecimal.valueOf(Double.parseDouble(dto.getRateValue().replace(",","."))))
                .dateFrom(LocalDate.parse(dto.getDateFrom()))
                .build();
        return build;
    }

    public RateOvertimeDto toDto(RateOvertime rate){
        RateOvertimeDto build = RateOvertimeDto.builder()
                .idRate(rate.getIdRate())
                .rateValue(rate.getRateValue().toString())
                .dateFrom(rate.getDateFrom().toString())
                .build();
        return build;
    }
}
