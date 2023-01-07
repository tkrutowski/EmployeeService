package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Builder
public class RateOvertime implements Comparable<RateOvertime> {
    Integer idRate;
    private LocalDate dateFrom;
    private BigDecimal rateValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RateOvertime)) return false;
        RateOvertime that = (RateOvertime) o;
        return getDateFrom().equals(that.getDateFrom()) &&
                getRateValue().equals(that.getRateValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateFrom(), getRateValue());
    }

    @Override
    public int compareTo(RateOvertime rateOvertime) {
        return getDateFrom().compareTo(rateOvertime.getDateFrom());
    }

    @Override
    public String toString() {
        return String.format("%.2f zł/h", rateValue);
    }
}