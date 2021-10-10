package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.focik.hr.employee.domain.share.RateType;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
class RateRegular {
    //Integer idRate;
    private RateType rateType;
    private LocalDate dateFrom;
    private BigDecimal rateValue;
}
