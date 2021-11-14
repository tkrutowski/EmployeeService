package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.EmployeeDto;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ApiEmployeeMapper {

    public Employee toDomain (EmployeeDto dto){
        Employee build = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .numberDaysOffLeft(dto.getNumberDaysOffLeft())
                .numberDaysOffAnnually(dto.getNumberDaysOffAnnually())
                .telNumber(dto.getTelNumber())
                .email(dto.getEmail())
                .otherInfo(dto.getOtherInfo())
                .employmentStatus(EmploymentStatus.valueOf(dto.getEmploymentStatus()))
                .hiredDate(dto.getHiredDate())
                .releaseDate(dto.getReleaseDate())
                .nextMedicalExaminationDate(dto.getNextMedicalExaminationDate())
                .nextBhpTrainingDate(dto.getNextBhpTrainingDate())
                .workTime(WorkTime.valueOf(dto.getWorkTime()))
                .employeeType(EmployeeType.valueOf(dto.getEmployeeType()))
                .build();
                build.setAddress(dto.getCity(), dto.getStreet(), dto.getZip());
                build.setRateRegular(null, RateType.valueOf(dto.getRateRegularType()), dto.getRateRegularDateFrom(), BigDecimal.valueOf(dto.getRateRegularValue()));
                build.setRateOvertime( null, dto.getRateOvertimeDateFrom(), BigDecimal.valueOf(dto.getRateOvertimeValue()));
        return build;
    }

    public EmployeeDto toDto(Employee e){
        EmployeeDto build = EmployeeDto.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .numberDaysOffLeft(e.getNumberDaysOffLeft())
                .numberDaysOffAnnually(e.getNumberDaysOffAnnually())
                .telNumber(e.getTelNumber())
                .otherInfo(e.getOtherInfo())
                .email(e.getEmail())
                .employmentStatus(e.getEmploymentStatus().toString())
                .hiredDate(e.getHiredDate())
                .releaseDate(e.getReleaseDate())
                .nextMedicalExaminationDate(e.getNextMedicalExaminationDate())
                .nextBhpTrainingDate(e.getNextBhpTrainingDate())
                .workTime(e.getWorkTime().toString())
                .employeeType(e.getEmployeeType().toString())
                .city(e.getCity())
                .street(e.getStreet())
                .zip(e.getZip())
                .rateRegularType(e.getLatestRateRegularType().toString())
                .rateRegularDateFrom(e.getLatestRateRegularDateFrom())
                .rateRegularValue(e.getLatestRateRegularValue().doubleValue())
                .rateOvertimeDateFrom(e.getLatestRateRegularDateFrom())
                .rateOvertimeValue(e.getLatestRateOvertimeValue().doubleValue())
                .build();
        return build;
    }
}