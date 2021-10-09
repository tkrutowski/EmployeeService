package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Value
@Builder
public class EmployeeDto {

    private Integer id;
    private String firstName;
    private String lastName;


    private float numberDaysOffLeft;
    private int numberDaysOffAnnually;

    private String telNumber;
    private String otherInfo;
    private String employmentStatus;

    private LocalDate hiredDate;
    private LocalDate releaseDate;
    private LocalDate nextMedicalExaminationDate;
    private LocalDate nextBhpTrainingDate;

    private String workTime;

    private String employeeType;

    private String city;
    private String street;
    private String zip;

    private String rateRegularType;
    private LocalDate rateRegularDateFrom;
    private double rateRegularValue;

    private LocalDate rateOvertimeDateFrom;
    private double rateOvertimeValue;
}
