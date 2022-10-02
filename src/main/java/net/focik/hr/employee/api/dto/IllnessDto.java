package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IllnessDto {
    @NotNull
    private Integer idEmployee;
    @NotEmpty
    private String date;
    @NotEmpty
    private Integer idIllnessType;
}