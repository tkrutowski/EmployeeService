package net.focik.hr.employee.domain.worktimerecords;

import lombok.RequiredArgsConstructor;
import net.focik.hr.employee.domain.exceptions.WorkTimeAlreadyExistException;
import net.focik.hr.employee.domain.exceptions.WorkTimeNotValidException;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.WorkTime;
import net.focik.hr.employee.domain.worktimerecords.port.secondary.WorkTimeRepository;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;
import net.focik.hr.employee.domain.worktimerecords.share.WorkType;
import net.focik.hr.employee.infrastructure.dto.DayOffId;
import net.focik.hr.employee.infrastructure.dto.IllnessId;
import net.focik.hr.employee.infrastructure.dto.WorkId;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class WorkService {

    private final WorkTimeRepository workTimeRepository;

    void addWorkTime(IWorkTime workTime) {
        validate(workTime);
        isExists(workTime);
        workTimeRepository.add(workTime);
    }

    public List<IWorkTime> findAllByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        return workTimeRepository.findAllWorkTimeByIdEmployeeAndDateYearMonth(idEmployee, date);
    }

    private void isExists(IWorkTime workTime) {
        int id = 0;
        LocalDate date = null;
        if (workTime instanceof Work) {
            id = ((Work) workTime).getIdEmployee();
            date = ((Work) workTime).getDate();
        } else if (workTime instanceof Illness) {
            id = ((Illness) workTime).getIdEmployee();
            date = ((Illness) workTime).getDate();
        } else if (workTime instanceof DayOff) {
            id = ((DayOff) workTime).getIdEmployee();
            date = ((DayOff) workTime).getDate();
        }

        assert date != null;
        Optional<Work> workById = workTimeRepository.findWorkById(new WorkId(id, date));
        if (workById.isPresent())
            throw new WorkTimeAlreadyExistException("W dniu " + date + " pracownik był w pracy.");

        Optional<Illness> illnessById = workTimeRepository.findIllnessById(new IllnessId(id, date));
        if (illnessById.isPresent())
            throw new WorkTimeAlreadyExistException("W dniu " + date + " pracownik był na zasiłku chorobowym.");

        Optional<DayOff> dayOffById = workTimeRepository.findDayOffById(new DayOffId(id, date));
        if (dayOffById.isPresent())
            throw new WorkTimeAlreadyExistException("W dniu " + date + " pracownik był na urlopie.");
    }

    private void validate(IWorkTime w) {
        if (w == null)
            throw new NullPointerException("IWorkTime can't be null.");

        if (w instanceof Work) {
            validWork((Work) w);
        } else if (w instanceof Illness) {
            validIllness((Illness) w);
        } else if (w instanceof DayOff) {
            validDayOff((DayOff) w);
        } else
            throw new WorkTimeNotValidException("IWorkTime is not a Work class.");
    }

    public Map<Integer, String> getDayOffTypeMap() {
        Map<Integer, String> dayOffTypeMap = new HashMap<>();
        Arrays.stream(DayOffType.values())
                .forEach(dayOffType -> dayOffTypeMap.put(dayOffType.getIdValue(), dayOffType.getDbValue()));
        return dayOffTypeMap;
    }

    public void deleteWorkTime(Integer idEmployee, LocalDate date, WorkType workType) {
        switch (workType) {
            case WORK:
                workTimeRepository.deleteWorkById(new WorkId(idEmployee, date));
                break;
            case ILLNESS:
                workTimeRepository.deleteIllnessById(new IllnessId(idEmployee, date));
                break;
            case DAY_OFF:
                workTimeRepository.deleteDayOffById(new DayOffId(idEmployee, date));
                break;
        }
    }

    public String createAttendancePdf(List<EmployeeQueryBasicDto> employeeList, LocalDate date, DaysToWork daysToWork) {
        Map<String, List<EmployeeQueryBasicDto>> collect = new HashMap<>();
        collect.put(EmployeeType.CONSTRUCTION_MANAGER.name(), employeeList.stream()
                .filter(dto -> dto.getEmployeeType().equals(EmployeeType.CONSTRUCTION_MANAGER.name()))
                .collect(Collectors.toList()));
        collect.put(WorkTime.FULL_TIME.name(), employeeList.stream()
                .filter(dto -> !dto.getEmployeeType().equals(EmployeeType.CONSTRUCTION_MANAGER.name()))
                .filter(dto -> dto.getWorkTime().equals(WorkTime.FULL_TIME.name()))
                .collect(Collectors.toList()));
        collect.put(WorkTime.PART_TIME.name(), employeeList.stream()
                .filter(dto -> !dto.getEmployeeType().equals(EmployeeType.CONSTRUCTION_MANAGER.name()))
                .filter(dto -> dto.getWorkTime().equals(WorkTime.PART_TIME.name()))
                .collect(Collectors.toList()));

        return AttendancePdf.createPdf(date, collect, daysToWork);
    }

    public String createTimeSheetPdf(List<EmployeeQueryBasicDto> employeeList, LocalDate date, DaysToWork daysToWork) {
        List<String> collect = employeeList.stream()
                .map(dto -> dto.getFirstName() + " " + dto.getLastName())
                .collect(Collectors.toList());

        return TimeSheetPdf.createPdf(date, collect, daysToWork);
    }


    private void validWork(Work work) {
        if (work.getStartTime() == null)
            throw new NullPointerException("StartTime can't be null.");
        if (work.getStopTime() == null)
            throw new NullPointerException("StopTime can't be null.");
        if (work.getStartTime().isAfter(work.getStopTime()))
            throw new WorkTimeNotValidException("Czas zakończenia nie może być wcześniejszy niż czas rozpoczęcia.");
    }

    private void validIllness(Illness illness) {
        if (illness.getIdEmployee() == 0)
            throw new WorkTimeNotValidException("Musisz wybrać pracownika.");
        if (illness.getDate() == null)
            throw new NullPointerException("Date can't be null.");
        if (illness.getIllnessType().getIdValue() == 0)
            throw new WorkTimeNotValidException("Musisz wybrać rodzaj zasiłku chorobowegoe.");
    }

    private void validDayOff(DayOff dayOff) {
        if (dayOff.getIdEmployee() == 0)
            throw new WorkTimeNotValidException("Musisz wybrać pracownika.");
        if (dayOff.getDate() == null)
            throw new NullPointerException("Date can't be null.");
        if (dayOff.getDayOffType().getIdValue() == 0)
            throw new WorkTimeNotValidException("Musisz wybrać rodzaj urlopu.");
    }

}
