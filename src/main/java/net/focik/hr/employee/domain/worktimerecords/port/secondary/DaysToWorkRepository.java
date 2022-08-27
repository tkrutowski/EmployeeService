package net.focik.hr.employee.domain.worktimerecords.port.secondary;

import net.focik.hr.employee.domain.worktimerecords.DaysToWork;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DaysToWorkRepository {

    Optional<DaysToWork> findByDate(Integer year, Integer month);
}
