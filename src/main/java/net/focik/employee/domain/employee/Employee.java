package net.focik.employee.domain.employee;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;



//    private string street;
//    private string city;
//    private string zipCode;
    private float numberDaysOffLeft;
    private int numberDaysOffAnnually;
    private String telNumber;
    private String otherInfo;
    private Employment employment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hiredDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextMedicalExaminationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextBhpTrainingDate;

    private Address address;
    private RateRegular rateRegular;
    private RateOvertime rateOvertime;
}
