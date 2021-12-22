package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.WorkDto;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.exceptions.WorkTimeNotValidException;
import net.focik.hr.employee.domain.workTimeRecords.Work;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ApiWorkMapper {

    public Work toDomain(WorkDto dto){
        int i =0;
        valid(dto);
        return new Work(dto.getIdEmployee(),
                LocalDate.parse(dto.getDate()),
                LocalTime.parse(dto.getStartTime()),
                LocalTime.parse(dto.getStopTime()));
    }

    private void valid(WorkDto dto) {
        if(dto.getIdEmployee() == null)
            throw new EmployeeNotValidException("IdEmployee can't be null.");
        if(dto.getDate().isEmpty())
            throw new WorkTimeNotValidException("Date can't be empty.");
        if(dto.getStartTime().isEmpty())
            throw new WorkTimeNotValidException("Start time can't be empty.");
        if(dto.getStopTime().isEmpty())
            throw new WorkTimeNotValidException("Stop time can't be empty.");
    }
}
