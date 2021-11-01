package net.focik.hr.employee.domain.workTimeRecords.port.secondary;

import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public interface WorkTimeRepository {
    Integer add(IWorkTime  workTime);
    Optional<IWorkTime> findById(Integer id);
    List<IWorkTime> findAllByIdEmployeeAndDate(int idEmployee, LocalDate date);
}
