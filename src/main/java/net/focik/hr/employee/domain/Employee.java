package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

//AgregateRoot
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
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

    void setRateRegular(Set<RateRegular> rateRegular) {
        this.rateRegular = rateRegular;
    }

    private Set<RateRegular> rateRegular;
    private Set<RateOvertime> rateOvertime;

    public void setAddress(String city, String street, String zip) {
        this.address = new Address(city, street, zip);
    }



    public void setRateRegular(Integer id, RateType rateType, LocalDate fromDate, BigDecimal value) {
        if(rateRegular == null)
            rateRegular = new HashSet<>();
    this.rateRegular.add(new RateRegular(id, rateType, fromDate, value));
    }

    public void setRateOvertime(Integer id, LocalDate fromDate, BigDecimal value) {
        if(rateOvertime == null)
            rateOvertime = new HashSet<>();
        this.rateOvertime.add(new RateOvertime(id, fromDate, value));
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

    public LocalDate getLatestRateRegularDateFrom() {
        return rateRegular.stream()
                .sorted(RateRegular::compareTo)
                .findFirst()
                .get()
                .getDateFrom();// rateRegular.getDateFrom();
    }

    public BigDecimal getLatestRateRegularValue() {
        return rateRegular.stream()
                .sorted(RateRegular::compareTo)
                .findFirst()
                .get()
                .getRateValue();
        //rateRegular.getRateValue();
    }
    public BigDecimal getRateRegularValueByDate(LocalDate date) {
         return rateRegular.stream()
                .sorted(RateRegular::compareTo)
                .filter(rate -> rate.getDateFrom().isAfter(date))
                .findFirst()
                .get()
                .getRateValue();
        //rateRegular.getRateValue();
    }
    public RateType getLatestRateRegularType() {
        return
                rateRegular.stream()
                        .sorted(RateRegular::compareTo)
                        .findFirst()
                        .get()
                        .getRateType();
    }

    public RateType getRateRegularTypeByDate(LocalDate date) {
        return
                rateRegular.stream()
                        .sorted(RateRegular::compareTo)
                        .filter(rate -> rate.getDateFrom().isAfter(date))
                        .findFirst()
                        .get()
                        .getRateType();
    }

    public LocalDate getLatestRateOvertimeDateFrom() {
        return rateOvertime.stream()
                .sorted(RateOvertime::compareTo)
                .findFirst()
                .get()
                .getDateFrom();
        //getDateFrom();
    }

    public BigDecimal getLatestRateOvertimeValue() {
        return rateOvertime.stream()
                .sorted(RateOvertime::compareTo)

                .findFirst()
                .get()
                .getRateValue();
    }

    public BigDecimal getRateOvertimeValueByDate(LocalDate date) {
        return rateOvertime.stream()
                .sorted(RateOvertime::compareTo)
                .filter(rate -> rate.getDateFrom().isAfter(date))
                .findFirst()
                .get()
                .getRateValue();
    }
}
