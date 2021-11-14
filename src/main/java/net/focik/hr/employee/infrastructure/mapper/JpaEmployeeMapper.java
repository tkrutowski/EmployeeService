package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.infrastructure.dto.EmployeeDto;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JpaEmployeeMapper {

    public Employee toDomain(EmployeeDto dto, List<RateRegularDto> rateRegularDtos, List<RateOvertimeDto> rateOvertimeDtos){
        Employee employee = Employee.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .numberDaysOffLeft(dto.getNumberDaysOffLeft())
                .numberDaysOffAnnually(dto.getNumberDaysOffAnnually())
                .email(dto.getEmail())
                .telNumber(dto.getTelNumber())
                .otherInfo(dto.getOtherInfo())
                .employmentStatus(dto.getEmploymentStatus())
                .hiredDate(dto.getHiredDate())
                .releaseDate(dto.getReleaseDate())
                .nextMedicalExaminationDate(dto.getNextMedicalExaminationDate())
                .nextBhpTrainingDate(dto.getNextBhpTrainingDate())
                .workTime(dto.getWorkTime())
                .employeeType(dto.getEmployeeType())
                .rateOvertime(convertRateOvertimeListToSet(rateOvertimeDtos))
                .rateRegular(convertRateRegularListToSet(rateRegularDtos))
                .build();

                employee.setAddress(dto.getCity(), dto.getStreet(), dto.getZip());

        return employee;
    }

   public EmployeeDto toDto(Employee e){
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .numberDaysOffLeft(e.getNumberDaysOffLeft())
                .numberDaysOffAnnually(e.getNumberDaysOffAnnually())
                .telNumber(e.getTelNumber())
                .otherInfo(e.getOtherInfo())
                .employmentStatus(e.getEmploymentStatus())
                .hiredDate(e.getHiredDate())
                .releaseDate(e.getReleaseDate())
                .nextMedicalExaminationDate(e.getNextMedicalExaminationDate())
                .nextBhpTrainingDate(e.getNextBhpTrainingDate())
                .workTime(e.getWorkTime())
                .employeeType(e.getEmployeeType())
                .city(e.getCity())
                .street(e.getStreet())
                .zip(e.getZip())
                .email(e.getEmail())
                .build();

        return employeeDto;
    }

    public RateRegular toDomain(RateRegularDto dto){
        RateRegular rateRegular = RateRegular.builder()
                .idRate(dto.getIdRate())
                .dateFrom(dto.getDateFrom())
                .rateType(dto.getRateType())
                .rateValue(dto.getRateValue())
                .build();

        return rateRegular;
    }

    public RateOvertime toDomain(RateOvertimeDto dto){
        RateOvertime rateOvertime = RateOvertime.builder()
                .idRate(dto.getIdRate())
                .dateFrom(dto.getDateFrom())
                .rateValue(dto.getRateValue())
                .build();

        return rateOvertime;
    }

    private Set<RateRegular> convertRateRegularListToSet(List<RateRegularDto> rateRegularDtos) {
        Set<RateRegular> rateRegularSet = new HashSet<>();
        if(rateRegularDtos == null)
            return rateRegularSet;

        rateRegularDtos.stream()
                .forEach(rateRegularDto -> rateRegularSet.add(toDomain(rateRegularDto)));
        return rateRegularSet;
    }

    private Set<RateOvertime> convertRateOvertimeListToSet(List<RateOvertimeDto> rateOvertimeDtos) {
        Set<RateOvertime> rateOvertimeSet = new HashSet<>();
        if(rateOvertimeDtos == null)
            return rateOvertimeSet;

        rateOvertimeDtos.stream()
                .forEach(rateOvertimeDto ->  rateOvertimeSet.add(toDomain(rateOvertimeDto)));
        return rateOvertimeSet;
    }
}
