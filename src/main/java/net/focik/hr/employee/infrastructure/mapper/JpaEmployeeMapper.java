package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.infrastructure.dto.EmployeeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateOvertimeDbDto;
import net.focik.hr.employee.infrastructure.dto.RateRegularDbDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JpaEmployeeMapper {

    public Employee toDomain(EmployeeDbDto dto, List<RateRegularDbDto> rateRegularDbDtos, List<RateOvertimeDbDto> rateOvertimeDbDtos){
        Employee employee = Employee.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .numberDaysOffLeft(dto.getNumberDaysOffLeft())
                .numberDaysOffAnnually(dto.getNumberDaysOffAnnually())
                .email(dto.getEmail())
                .phoneNumber(dto.getTelNumber())
                .otherInfo(dto.getOtherInfo())
                .employmentStatus(dto.getEmploymentStatus())
                .hiredDate(dto.getHiredDate())
                .releaseDate(dto.getReleaseDate())
                .nextMedicalExaminationDate(dto.getNextMedicalExaminationDate())
                .nextBhpTrainingDate(dto.getNextBhpTrainingDate())
                .workTime(dto.getWorkTime())
                .employeeType(dto.getEmployeeType())
                .rateOvertime(convertRateOvertimeListToSet(rateOvertimeDbDtos))
                .rateRegular(convertRateRegularListToSet(rateRegularDbDtos))
                .pesel(dto.getPesel())
                .build();

                employee.setAddress(dto.getCity(), dto.getStreet(), dto.getZip());

        return employee;
    }

   public EmployeeDbDto toDto(Employee e){
        EmployeeDbDto employeeDto = EmployeeDbDto.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .numberDaysOffLeft(e.getNumberDaysOffLeft())
                .numberDaysOffAnnually(e.getNumberDaysOffAnnually())
                .telNumber(e.getPhoneNumber())
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
                .pesel(e.getPesel())
                .build();

        return employeeDto;
    }

    public RateRegular toDomain(RateRegularDbDto dto){
        RateRegular rateRegular = RateRegular.builder()
                .idRate(dto.getIdRate())
                .dateFrom(dto.getDateFrom())
                .rateType(dto.getRateType())
                .rateValue(dto.getRateValue())
                .build();

        return rateRegular;
    }

    public RateOvertime toDomain(RateOvertimeDbDto dto){
        RateOvertime rateOvertime = RateOvertime.builder()
                .idRate(dto.getIdRate())
                .dateFrom(dto.getDateFrom())
                .rateValue(dto.getRateValue())
                .build();

        return rateOvertime;
    }

    private Set<RateRegular> convertRateRegularListToSet(List<RateRegularDbDto> rateRegularDbDtos) {
        Set<RateRegular> rateRegularSet = new HashSet<>();
        if(rateRegularDbDtos == null)
            return rateRegularSet;

        rateRegularDbDtos.stream()
                .forEach(rateRegularDto -> rateRegularSet.add(toDomain(rateRegularDto)));
        return rateRegularSet;
    }

    private Set<RateOvertime> convertRateOvertimeListToSet(List<RateOvertimeDbDto> rateOvertimeDbDtos) {
        Set<RateOvertime> rateOvertimeSet = new HashSet<>();
        if(rateOvertimeDbDtos == null)
            return rateOvertimeSet;

        rateOvertimeDbDtos.stream()
                .forEach(rateOvertimeDto ->  rateOvertimeSet.add(toDomain(rateOvertimeDto)));
        return rateOvertimeSet;
    }
}
