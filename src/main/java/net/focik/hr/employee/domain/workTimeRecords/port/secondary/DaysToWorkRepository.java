package net.focik.hr.employee.domain.workTimeRecords.port.secondary;

import net.focik.hr.employee.domain.workTimeRecords.DaysToWork;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DaysToWorkRepository {

    Optional<DaysToWork> findByDate(Integer year, Integer month);
}
