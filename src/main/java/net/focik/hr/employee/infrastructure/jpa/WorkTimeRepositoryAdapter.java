package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.worktimerecords.DayOff;
import net.focik.hr.employee.domain.worktimerecords.IWorkTime;
import net.focik.hr.employee.domain.worktimerecords.Illness;
import net.focik.hr.employee.domain.worktimerecords.Work;
import net.focik.hr.employee.domain.worktimerecords.port.secondary.WorkTimeRepository;
import net.focik.hr.employee.infrastructure.dto.*;
import net.focik.hr.employee.infrastructure.mapper.JpaWorkMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
@AllArgsConstructor
class WorkTimeRepositoryAdapter implements WorkTimeRepository {

    WorkDtoRepository workDtoRepository;
    IllnessDtoRepository illnessDtoRepository;
    DayOffDtoRepository dayOffDtoRepository;
    JpaWorkMapper mapper;

    @Override
    public void add(IWorkTime workTime) {
        if (workTime instanceof Work)
            workDtoRepository.save(mapper.toDto((Work) workTime));
        else if (workTime instanceof Illness)
            illnessDtoRepository.save(mapper.toDto((Illness) workTime));
        else if (workTime instanceof DayOff)
            dayOffDtoRepository.save(mapper.toDto((DayOff) workTime));
    }

    @Override
    public Optional<Work> findWorkById(WorkId id) {
        Optional<WorkDto> byId = workDtoRepository.findById(id);
        if (byId.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.toDomain(byId.get()));
    }

    @Override
    public Optional<Illness> findIllnessById(IllnessId id) {
        Optional<IllnessDto> byId = illnessDtoRepository.findById(id);
        if (byId.isEmpty())
            return Optional.empty();
        return Optional.of(mapper.toDomain(byId.get()));
    }

    @Override
    public Optional<DayOff> findDayOffById(DayOffId id) {
        Optional<DayOffDto> byId = dayOffDtoRepository.findById(id);
        if (byId.isEmpty())
            return Optional.empty();
        return Optional.of(mapper.toDomain(byId.get()));
    }

    @Override
    public List<IWorkTime> findAllWorkTimeByIdEmployeeAndDateYearMonth(int idEmployee, LocalDate date) {
        List<IWorkTime> iWorkTimes = new ArrayList<>();
        String dateFormat = date.getYear() + String.format("-%02d", date.getMonthValue());
//        workDtoRepository.findAllByIdEmployeeAndDate(idEmployee, date).stream()
        workDtoRepository.findAllByIdEmployeeAndDate(idEmployee, dateFormat)
                .forEach(workDto -> iWorkTimes.add(mapper.toDomain(workDto)));
        dayOffDtoRepository.findAllByIdEmployeeAndDate(idEmployee, dateFormat)
                .forEach(dayOffDto -> iWorkTimes.add(mapper.toDomain(dayOffDto)));
        illnessDtoRepository.findAllByIdEmployeeAndDate(idEmployee, dateFormat)
                .forEach(illnessDto -> iWorkTimes.add(mapper.toDomain(illnessDto)));
        return iWorkTimes;
    }
}
