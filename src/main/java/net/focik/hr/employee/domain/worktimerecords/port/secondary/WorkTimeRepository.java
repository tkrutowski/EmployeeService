package net.focik.hr.employee.domain.worktimerecords.port.secondary;

import net.focik.hr.employee.domain.worktimerecords.DayOff;
import net.focik.hr.employee.domain.worktimerecords.IWorkTime;
import net.focik.hr.employee.domain.worktimerecords.Illness;
import net.focik.hr.employee.domain.worktimerecords.Work;
import net.focik.hr.employee.infrastructure.dto.DayOffId;
import net.focik.hr.employee.infrastructure.dto.IllnessId;
import net.focik.hr.employee.infrastructure.dto.WorkId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public interface WorkTimeRepository {
    void add(IWorkTime  workTime);

    Optional<Work> findWorkById(WorkId id);

    Optional<Illness> findIllnessById(IllnessId id);
    Optional<DayOff> findDayOffById(DayOffId id);
    List<IWorkTime> findAllWorkTimeByIdEmployeeAndDateYearMonth(int idEmployee, LocalDate date);
}
