package net.focik.hr.employee.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.focik.hr.employee.domain.workTimeRecords.share.WorkType;

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
    private Boolean isHoliday;
    private Integer idEmployee;
    private WorkType workType;
}
