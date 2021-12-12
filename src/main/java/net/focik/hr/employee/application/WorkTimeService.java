package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.port.primary.GetWokTimeRecordsUseCase;
import net.focik.hr.employee.domain.workTimeRecords.IWorkTime;
import net.focik.hr.employee.domain.workTimeRecords.WorkTimeFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class WorkTimeService implements GetWokTimeRecordsUseCase {
    WorkTimeFacade workTimeFacade;


    @Override
    public List<IWorkTime> getWorkTimeByEmployeeAndDate(Integer idEmployee, LocalDate date) {

        return workTimeFacade.getAllByEmployeeIdAndDate(idEmployee, date);

    }
}
