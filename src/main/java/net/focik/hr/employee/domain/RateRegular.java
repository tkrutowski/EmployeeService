package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.focik.hr.employee.domain.share.RateType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Builder
public class RateRegular implements Comparable<RateRegular> {
    private Integer idRate;
    private RateType rateType;
    private LocalDate dateFrom;
    private BigDecimal rateValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RateRegular)) return false;
        RateRegular that = (RateRegular) o;
        return getRateType() == that.getRateType() &&
                getDateFrom().equals(that.getDateFrom()) &&
                getRateValue().equals(that.getRateValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRateType(), getDateFrom(), getRateValue());
    }

    @Override
    public int compareTo(RateRegular rateRegular) {
        return getDateFrom().compareTo(rateRegular.getDateFrom());
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", rateValue, rateType.equals(RateType.PER_HOUR) ? "zł/h" : "zł/mc");
    }
}