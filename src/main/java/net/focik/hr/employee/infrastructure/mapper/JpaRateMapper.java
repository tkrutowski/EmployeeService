package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDto;
import org.springframework.stereotype.Component;

@Component
public class JpaRateMapper {


    public RateRegular toDomain (RateRegularDto dto){
        RateRegular rateRegular = RateRegular.builder()
                .idRate(dto.getIdRate())
                .rateType(dto.getRateType())
                .dateFrom(dto.getDateFrom())
                .rateValue(dto.getRateValue())
                .build();

        return rateRegular;
    }

    public RateOvertime toDomain (RateOvertimeDto dto){
        RateOvertime rateOvertime = RateOvertime.builder()
                .idRate(dto.getIdRate())
                .dateFrom(dto.getDateFrom())
                .rateValue(dto.getRateValue())
                .build();

        return rateOvertime;
    }

    public RateRegularDto toDto(RateRegular rateRegular, Integer idEmployee){
        RateRegularDto rateRegularDto = RateRegularDto.builder()
                .idRate(rateRegular.getIdRate())
                .idEmployee(idEmployee)
                .dateFrom(rateRegular.getDateFrom())
                .rateType(rateRegular.getRateType())
                .rateValue(rateRegular.getRateValue())
                .build();

        return rateRegularDto;
    }

    public RateOvertimeDto toDto(RateOvertime rateOvertime, Integer idEmployee){
        RateOvertimeDto overtimeDto = RateOvertimeDto.builder()
                .idRate(rateOvertime.getIdRate())
                .idEmployee(idEmployee)
                .dateFrom(rateOvertime.getDateFrom())
                .rateValue(rateOvertime.getRateValue())
                .build();

        return overtimeDto;
    }

}
