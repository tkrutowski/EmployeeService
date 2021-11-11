package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.infrastructure.dto.EmployeeDto;
import org.springframework.stereotype.Component;

@Component
public class JpaEmployeeMapper {

    public Employee toDomain (EmployeeDto dto){
        Employee employee = Employee.builder()
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

}
