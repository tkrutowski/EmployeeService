package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.focik.hr.employee.domain.share.RateType;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RateRegularDto {
    private Integer idRate;
    private String rateType;
    private String dateFrom;
    private String rateValue;

}
