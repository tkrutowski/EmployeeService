package net.focik.hr.employee.domain;

import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

//entity
@ToString
public class RateRegular extends Rate {
    private RateType rateType;

    public RateRegular(Integer idRate, Integer idEmployee, LocalDate dateFrom, BigDecimal rateValue, RateType rateType) {
        super(idRate, idEmployee, dateFrom, rateValue);
        this.rateType = rateType;
    }

    @Override
    public void setId(Integer id) {
        this.idRate = id;
    }
}
