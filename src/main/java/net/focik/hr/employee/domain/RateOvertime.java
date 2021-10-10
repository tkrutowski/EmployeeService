package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
class RateOvertime {
    //Integer idRate;
    private LocalDate dateFrom;
    private BigDecimal rateValue;
}
