package net.focik.hr.employee.domain.addition;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@ToString
public class Addition  {
    protected Integer id;
    private BigDecimal amount;
    private LocalDate date;
    private String otherInfo;
    AdditionType additionType;

    public Integer getAdditionTypeId() {
        return additionType.getId();
    }

    public String getAdditionTypeName() {
        return additionType.getName();
    }
}
