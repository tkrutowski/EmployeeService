package net.focik.hr.employee.domain;

import lombok.*;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.RateType;
import net.focik.hr.employee.domain.share.WorkTime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    private String phoneNumber;
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
    private Integer idTeam;
    private String pesel;

    private List<RateRegular> rateRegular;
    private List<RateOvertime> rateOvertime;

    public void setAddress(String city, String street, String zip) {
        this.address = new Address(city, street, zip);
    }


    public void setRateRegular(Integer id, RateType rateType, LocalDate fromDate, BigDecimal value) {
        if (rateRegular == null)
            rateRegular = new ArrayList<>();
        this.rateRegular.add(new RateRegular(id, rateType, fromDate, value));
    }

    public void setRateRegular(List<RateRegular> rateRegular) {
        this.rateRegular = rateRegular;
    }

    public void setRateOvertime(Integer id, LocalDate fromDate, BigDecimal value) {
        if (rateOvertime == null)
            rateOvertime = new ArrayList<>();
        this.rateOvertime.add(new RateOvertime(id, fromDate, value));
    }

    public void setRateOvertime(List<RateOvertime> rateOvertime) {
        this.rateOvertime = rateOvertime;
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


    public RateRegular getLatestRateRegular() {
        return rateRegular.stream()
                .sorted(RateRegular::compareTo)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    public RateRegular getRateRegularByDate(LocalDate date) {
        return rateRegular.stream()
                .sorted(Comparator.reverseOrder())
                .filter(rate -> rate.getDateFrom().isBefore(date))
                .findFirst().orElse(RateRegular.builder().rateValue(BigDecimal.ZERO).build());
    }

    public RateOvertime getRateOvertimeByDate(LocalDate date) {
        return rateOvertime.stream()
                .sorted(RateOvertime::compareTo)
                .sorted(Comparator.reverseOrder())
                .filter(rate -> rate.getDateFrom().isBefore(date))
                .findFirst().orElse(RateOvertime.builder().rateValue(BigDecimal.ZERO).build());
    }

    public RateOvertime getLatestRateOvertime() {
        return rateOvertime.stream()
                .sorted(RateOvertime::compareTo)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    public void changeEmploymentStatus(EmploymentStatus newEmploymentStatus) {
        this.employmentStatus = newEmploymentStatus;
    }
}
