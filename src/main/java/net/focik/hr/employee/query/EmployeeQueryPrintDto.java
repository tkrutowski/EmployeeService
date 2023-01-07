package net.focik.hr.employee.query;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeQueryPrintDto {

    private Integer id;
    private String firstName;
    private String lastName;

    private String employmentStatus;
    private String employeeType;
    private String workTime;
}