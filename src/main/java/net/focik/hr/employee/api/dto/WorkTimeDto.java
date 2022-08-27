package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.focik.hr.employee.domain.worktimerecords.share.WorkType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WorkTimeDto {
    private String date;
    private String dayOfWeek;
    private String startTime;
    private String stopTime;
    private String workTimeAll;
    private String workTime50;
    private String workTime100;
    @Setter private Boolean isHoliday;
    private Integer idEmployee;
    private WorkType workType;
    private Integer idIllnessType;
    private Integer idDayOffType;
}
