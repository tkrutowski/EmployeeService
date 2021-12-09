package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.focik.hr.employee.domain.share.RateType;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DayOffTypeDto {
    private Integer id;
    private String name;

}
