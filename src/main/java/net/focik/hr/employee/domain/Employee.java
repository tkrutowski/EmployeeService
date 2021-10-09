package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.WorkTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;

//AgregateRoot
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "pracownik")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pracownika")
    @Setter
    protected Integer id;
    @Column(name = "imie")
    private String firstName;
    @Column(name = "nazwisko")
    private String lastName;


    @Column(name = "urlop_pozostaly")
    private float numberDaysOffLeft;
    @Column(name = "wymiar_urlopu")
    private int numberDaysOffAnnually;

    @Column(name = "numer")
    private String telNumber;
    @Column(name = "inne")
    private String otherInfo;
    //todo zmienić w bazie z bit na varchar
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @Column(name = "data_zatrudnienia")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hiredDate;
    @Column(name = "data_zwolnienia")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Column(name = "data_nast_badania_lek")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextMedicalExaminationDate;
    @Column(name = "data_nast_szkolenia_bhp")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextBhpTrainingDate;

    //todo zmienić w bazie z bit na varchar
    @Enumerated(EnumType.STRING)
    @Column(name = "pol_etatu")
    private WorkTime workTime;

    //todo zmienić w bazie z bit na varchar
    @Enumerated(EnumType.STRING)
    @Column(name = "czy_kadra")
    private EmployeeType employeeType;

    private Address address;

    @Setter
    @Transient
    private RateRegular rateRegular;

    @Setter
    @Transient
    private RateOvertime rateOvertime;
}
