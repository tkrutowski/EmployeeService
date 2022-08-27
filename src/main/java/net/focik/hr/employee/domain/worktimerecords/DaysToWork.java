package net.focik.hr.employee.domain.worktimerecords;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Builder
public class DaysToWork {

    @Getter
    private Integer hoursToWork;
    @Getter
    private Map<LocalDate, String> holidays;
}
