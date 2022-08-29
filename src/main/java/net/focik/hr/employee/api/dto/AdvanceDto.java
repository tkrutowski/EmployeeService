package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdvanceDto {
    private int id;
    private int idEmployee;
    private String amount;
    private String date;
    private String otherInfo;
}
