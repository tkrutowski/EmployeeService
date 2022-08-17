package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import org.springframework.stereotype.Component;

@Component
public class JpaRateMapper {


    public RateRegular toDomain (RateRegularDbDto dto){
        RateRegular rateRegular = RateRegular.builder()
                .idRate(dto.getIdRate())
                .rateType(dto.getRateType())
                .dateFrom(dto.getDateFrom())
                .rateValue(dto.getRateValue())
                .build();

        return rateRegular;
    }

    public RateOvertime toDomain (RateOvertimeDbDto dto){
        RateOvertime rateOvertime = RateOvertime.builder()
                .idRate(dto.getIdRate())
                .dateFrom(dto.getDateFrom())
                .rateValue(dto.getRateValue())
                .build();

        return rateOvertime;
    }

    public RateRegularDbDto toDto(RateRegular rateRegular, Integer idEmployee){
        RateRegularDbDto rateRegularDbDto = RateRegularDbDto.builder()
                .idRate(rateRegular.getIdRate())
                .idEmployee(idEmployee)
                .dateFrom(rateRegular.getDateFrom())
                .rateType(rateRegular.getRateType())
                .rateValue(rateRegular.getRateValue())
                .build();

        return rateRegularDbDto;
    }

    public RateOvertimeDbDto toDto(RateOvertime rateOvertime, Integer idEmployee){
        RateOvertimeDbDto overtimeDto = RateOvertimeDbDto.builder()
                .idRate(rateOvertime.getIdRate())
                .idEmployee(idEmployee)
                .dateFrom(rateOvertime.getDateFrom())
                .rateValue(rateOvertime.getRateValue())
                .build();

        return overtimeDto;
    }

}
