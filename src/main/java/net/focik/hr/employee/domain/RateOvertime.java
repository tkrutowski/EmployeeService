package net.focik.hr.employee.domain;

import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

//entity
@ToString
public class RateOvertime extends Rate {

    public RateOvertime(Integer idRate, Integer idEmployee, LocalDate dateFrom, BigDecimal rateValue) {
        super(idRate, idEmployee, dateFrom, rateValue);
    }

    @Override
    public void setId(Integer id) {
        this.idRate=id;
    }
}
