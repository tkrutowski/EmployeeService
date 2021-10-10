package net.focik.hr.employee.infrastructure.dto;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JpaMapper {

    Employee toDomain (EmployeeDto dto){
        Employee employee = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .numberDaysOffLeft(dto.getNumberDaysOffLeft())
                .numberDaysOffAnnually(dto.getNumberDaysOffAnnually())
                .telNumber(dto.getTelNumber())
                .otherInfo(dto.getOtherInfo())
                .employmentStatus(dto.getEmploymentStatus())
                .hiredDate(dto.getHiredDate())
                .releaseDate(dto.getReleaseDate())
                .nextMedicalExaminationDate(dto.getNextMedicalExaminationDate())
                .nextBhpTrainingDate(dto.getNextBhpTrainingDate())
                .workTime(dto.getWorkTime())
                .employeeType(dto.getEmployeeType())
                .build();

                employee.setAddress(dto.getCity(), dto.getStreet(), dto.getZip());
              //  employee.setRateRegular(RateType.valueOf(dto.getRateRegularType()), dto.getRateRegularDateFrom(), BigDecimal.valueOf(dto.getRateRegularValue())))
            //    .rateOvertime(new RateOvertime(null, null, dto.getRateOvertimeDateFrom(), BigDecimal.valueOf(dto.getRateOvertimeValue())))
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
//                .rateRegularType(e.getRateRegular().getRateType().toString())
//                .rateRegularDateFrom(e.getRateRegular().getDateFrom())
//                .rateRegularValue(e.getRateRegular().getRateValue().doubleValue())
//                .rateOvertimeDateFrom(e.getRateOvertime().getDateFrom())
//                .rateOvertimeValue(e.getRateOvertime().getRateValue().doubleValue())
                .build();

        return employeeDto;
    }
}
