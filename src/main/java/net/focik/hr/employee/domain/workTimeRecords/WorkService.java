package net.focik.hr.employee.domain.workTimeRecords;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.exceptions.WorkTimeNotValidException;
import net.focik.hr.employee.domain.workTimeRecords.port.secondary.WorkTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
class WorkService {

    private WorkTimeRepository workTimeRepository;

    Integer addWorkTime(IWorkTime workTime) {
        validate(workTime);
        return workTimeRepository.add(workTime);
    }


    public List<IWorkTime> findAllByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        return workTimeRepository.findAllByIdEmployeeAndDate(idEmployee,date);
    }

    private void validate(IWorkTime w){
        if(w == null)
            throw new NullPointerException("IWorkTime can't be null.");
        if(w instanceof Work == false)
            throw new WorkTimeNotValidException("IWorkTime is not a Work class.");

        Work work = (Work) w;
        if(work.getStartTime() == null)
            throw new NullPointerException("StartTime can't be null.");
        if(work.getStopTime() == null)
            throw new NullPointerException("StopTime can't be null.");
        if(work.getStartTime().isAfter(work.getStopTime()))
            throw new WorkTimeNotValidException("Czas zakończenia nie może być wcześniejszy niż czas rozpoczęcia.");
    }


    public Integer findWorkMinutesByIdEmployeeAndDate(Integer idEmployee, LocalDate date) {
        return null;
    }
}
