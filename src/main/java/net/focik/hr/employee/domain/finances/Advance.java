package net.focik.hr.employee.domain.finances;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@ToString
public class Advance {
    protected Integer id;
    private BigDecimal amount;
    private LocalDate date;
    private String otherInfo;
}
