package net.focik.hr.employee.query;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeQueryBasicDto {

    private Integer id;
    private String firstName;
    private String lastName;

    private String street;
    private String city;
    private String zip;
    private String telNumber;
    private String otherInfo;
    private String email;

    private LocalDate hiredDate;
    private LocalDate releaseDate;
    private LocalDate nextMedicalExaminationDate;
    private LocalDate nextBhpTrainingDate;

    private String numberDaysOffLeft;
    private String numberDaysOffAnnually;

    private String employmentStatus;

    private String pesel;

    private int idTeam;
    private String employeeType;
    private String workTime;
}