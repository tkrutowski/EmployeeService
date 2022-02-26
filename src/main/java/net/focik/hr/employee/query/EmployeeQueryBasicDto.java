package net.focik.hr.employee.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.focik.hr.employee.domain.share.RateType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class EmployeeQueryBasicDto {

    private Integer id;
    private String firstName;
    private String lastName;

//    private String street;
//    private String city;
//    private String zipCode;
//    private String telNumber;
//    private String otherInfo;
//    private String email;

}
