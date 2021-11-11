package net.focik.hr.employee.domain.addition;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@ToString
public class Addition  {
    @Getter protected Integer id;
    @Getter private int idEmployee;
    @Getter private BigDecimal amount;
    @Getter private LocalDate date;
    @Getter private String otherInfo;
    private AdditionType additionType;

    public Integer getAdditionTypeId() {
        return additionType.getId();
    }

    public String getAdditionTypeName() {
        return additionType.getName();
    }
}
