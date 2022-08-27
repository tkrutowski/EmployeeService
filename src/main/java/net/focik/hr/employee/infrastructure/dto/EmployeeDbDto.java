package net.focik.hr.employee.infrastructure.dto;

import lombok.*;
import net.focik.hr.employee.domain.share.EmployeeType;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.domain.share.WorkTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "pracownik")
public class EmployeeDbDto {

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
    @Column(name = "zatrudniony")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "pol_etatu")
    private WorkTime workTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "czy_kadra")
    private EmployeeType employeeType;

    @Column(name = "miasto")
    private String city;
    @Column(name = "ulica")
    private String street;
    @Column(name = "kod")
    private String zip;

    @Column(name = "email")
    private String email;

    private String pesel;

    private int idTeam;
}

