package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.focik.hr.employee.domain.share.RateType;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class RateRegularDto {
    private Integer idRate;
    private Integer idEmployee;
    private RateType rateType;
    private LocalDate dateFrom;
    private BigDecimal rateValue;

}
