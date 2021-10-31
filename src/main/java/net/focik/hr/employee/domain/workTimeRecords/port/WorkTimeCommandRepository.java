package net.focik.hr.employee.domain.workTimeRecords.port;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface WorkTimeCommandRepository {
    Integer add(IWorkTime  workTime);
    Optional<IWorkTime> findById(Integer id);
}
