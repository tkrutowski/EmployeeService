package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.exceptions.DaysToWorkNotFoundException;
import net.focik.hr.employee.domain.workTimeRecords.port.secondary.DaysToWorkRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class DaysToWorkService {

    DaysToWorkRepository daysToWorkRepository;

    public DaysToWork findDaysToWorkByDate(Integer year, Integer month) {
        Optional<DaysToWork> byDate = daysToWorkRepository.findByDate(year, month);

        if (byDate.isEmpty())
            throw new DaysToWorkNotFoundException(year, month);
        return byDate.get();
    }
}
