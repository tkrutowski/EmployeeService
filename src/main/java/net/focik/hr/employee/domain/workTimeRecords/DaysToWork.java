package net.focik.hr.employee.domain.workTimeRecords;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Builder
public class DaysToWork {

    @Getter
    private Integer hoursToWork;
    private Map<LocalDate, String> holidays;
}
