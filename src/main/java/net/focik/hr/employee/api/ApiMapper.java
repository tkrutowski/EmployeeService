package net.focik.hr.employee.api;

import net.focik.hr.employee.domain.Address;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.RateOvertime;
import net.focik.hr.employee.domain.RateRegular;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;

import java.math.BigDecimal;

public class ApiMapper {

    Employee toDomain (EmployeeDto dto){
        Employee build = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .numberDaysOffLeft(dto.getNumberDaysOffLeft())
                .numberDaysOffAnnually(dto.getNumberDaysOffAnnually())
                .telNumber(dto.getTelNumber())
                .otherInfo(dto.getOtherInfo())
                .employmentStatus(EmploymentStatus.valueOf(dto.getEmploymentStatus()))
                .hiredDate(dto.getHiredDate())
                .releaseDate(dto.getReleaseDate())
                .nextMedicalExaminationDate(dto.getNextMedicalExaminationDate())
                .nextBhpTrainingDate(dto.getNextBhpTrainingDate())
                .workTime(WorkTime.valueOf(dto.getWorkTime()))
                .employeeType(EmployeeType.valueOf(dto.getEmployeeType()))
                .address(new Address(dto.getCity(), dto.getStreet(), dto.getZip()))
                .rateRegular(new RateRegular(null, null, RateType.valueOf(dto.getRateRegularType()), dto.getRateRegularDateFrom(), BigDecimal.valueOf(dto.getRateRegularValue())))
                .rateOvertime(new RateOvertime(null, null, dto.getRateOvertimeDateFrom(), BigDecimal.valueOf(dto.getRateOvertimeValue())))
                .build();
        return build;
    }

    EmployeeDto toDto(Employee e){
        EmployeeDto build = EmployeeDto.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .numberDaysOffLeft(e.getNumberDaysOffLeft())
                .numberDaysOffAnnually(e.getNumberDaysOffAnnually())
                .telNumber(e.getTelNumber())
                .otherInfo(e.getOtherInfo())
                .employmentStatus(e.getEmploymentStatus().toString())
                .hiredDate(e.getHiredDate())
                .releaseDate(e.getReleaseDate())
                .nextMedicalExaminationDate(e.getNextMedicalExaminationDate())
                .nextBhpTrainingDate(e.getNextBhpTrainingDate())
                .workTime(e.getWorkTime().toString())
                .employeeType(e.getEmployeeType().toString())
                .city(e.getAddress().getCity())
                .street(e.getAddress().getStreet())
                .zip(e.getAddress().getZip())
                .rateRegularType(e.getRateRegular().getRateType().toString())
                .rateRegularDateFrom(e.getRateRegular().getDateFrom())
                .rateRegularValue(e.getRateRegular().getRateValue().doubleValue())
                .rateOvertimeDateFrom(e.getRateOvertime().getDateFrom())
                .rateOvertimeValue(e.getRateOvertime().getRateValue().doubleValue())
                .build();

        return build;
    }
}
