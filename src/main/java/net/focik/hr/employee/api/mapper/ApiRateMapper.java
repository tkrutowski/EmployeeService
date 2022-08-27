package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.RateOvertimeDto;
import net.focik.hr.employee.api.dto.RateRegularDto;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.share.RateType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ApiRateMapper {

    public RateRegular toDomain(RateRegularDto dto) {
        return RateRegular.builder()
                .idRate(dto.getIdRate())
                .rateValue(BigDecimal.valueOf(Double.parseDouble(dto.getRateValue().replace(",", "."))))
                .dateFrom(LocalDate.parse(dto.getDateFrom()))
                .rateType(RateType.valueOf(dto.getRateType()))
                .build();
    }

    public RateRegularDto toDto(RateRegular rate) {
        return RateRegularDto.builder()
                .idRate(rate.getIdRate())
                .rateValue(rate.getRateValue().toString())
                .rateType(rate.getRateType().toString())
                .dateFrom(rate.getDateFrom().toString())
                .build();
    }

    public RateOvertime toDomain(RateOvertimeDto dto) {
        return RateOvertime.builder()
                .idRate(dto.getIdRate())
                .rateValue(BigDecimal.valueOf(Double.parseDouble(dto.getRateValue().replace(",", "."))))
                .dateFrom(LocalDate.parse(dto.getDateFrom()))
                .build();
    }

    public RateOvertimeDto toDto(RateOvertime rate) {
        return RateOvertimeDto.builder()
                .idRate(rate.getIdRate())
                .rateValue(rate.getRateValue().toString())
                .dateFrom(rate.getDateFrom().toString())
                .build();
    }
}
