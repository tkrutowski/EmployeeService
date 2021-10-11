package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;

import java.math.BigDecimal;
import java.time.LocalDate;

//AgregateRoot
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Employee {
    protected Integer id;
    private String firstName;
    private String lastName;
    private Float numberDaysOffLeft;
    private Integer numberDaysOffAnnually;
    private String telNumber;
    private String otherInfo;
    private String email;
    private EmploymentStatus employmentStatus;
    private LocalDate hiredDate;
    private LocalDate releaseDate;
    private LocalDate nextMedicalExaminationDate;
    private LocalDate nextBhpTrainingDate;
    private WorkTime workTime;
    private EmployeeType employeeType;
    private Address address;
    private RateRegular rateRegular;
    private RateOvertime rateOvertime;

    public void setAddress(String city, String street, String zip){
        this.address = new Address(city,street,zip);
    }

    public void setRateRegular(RateType rateType, LocalDate fromDate, BigDecimal value) {
        this.rateRegular = new RateRegular(rateType, fromDate, value);
    }

    public void setRateOvertime(LocalDate fromDate, BigDecimal value) {
        this.rateOvertime = new RateOvertime(fromDate, value);
    }


    public String getCity() {
        return address.getCity();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getZip() {
        return address.getZip();
    }

    public LocalDate getRateRegularDateFrom() {
        return rateRegular.getDateFrom();
    }

    public BigDecimal getRateRegularValue() {
        return rateRegular.getRateValue();
    }

    public RateType getRateRegularType() {
        return rateRegular.getRateType();
    }

    public LocalDate getRateOvertimeDateFrom() {
        return rateOvertime.getDateFrom();
    }

    public BigDecimal getRateOvertimeValue() {
        return rateOvertime.getRateValue();
    }

}
