package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import org.springframework.stereotype.Component;

@Component
public class JpaRateMapper {


    public RateRegular toDomain(RateRegularDbDto dto) {
        return RateRegular.builder()
                .idRate(dto.getIdRate())
                .rateType(dto.getRateType())
                .dateFrom(dto.getDateFrom())
                .rateValue(dto.getRateValue())
                .build();
    }

    public RateOvertime toDomain(RateOvertimeDbDto dto) {
        return RateOvertime.builder()
                .idRate(dto.getIdRate())
                .dateFrom(dto.getDateFrom())
                .rateValue(dto.getRateValue())
                .build();
    }

    public RateRegularDbDto toDto(RateRegular rateRegular, Integer idEmployee) {
        return RateRegularDbDto.builder()
                .idRate(rateRegular.getIdRate())
                .idEmployee(idEmployee)
                .dateFrom(rateRegular.getDateFrom())
                .rateType(rateRegular.getRateType())
                .rateValue(rateRegular.getRateValue())
                .build();
    }

    public RateOvertimeDbDto toDto(RateOvertime rateOvertime, Integer idEmployee) {
        return RateOvertimeDbDto.builder()
                .idRate(rateOvertime.getIdRate())
                .idEmployee(idEmployee)
                .dateFrom(rateOvertime.getDateFrom())
                .rateValue(rateOvertime.getRateValue())
                .build();
    }
}
