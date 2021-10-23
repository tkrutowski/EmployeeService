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
@NoArgsConstructor
@Getter
@Entity
@Table(name = "pracownik_view")
public class EmployeeQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pracownika")
    private Integer id;
    @Column(name = "imie")
    private String firstName;
    @Column(name = "nazwisko")
    private String lastName;
    @Column(name = "ulica")
    private String street;
    @Column(name = "miasto")
    private String city;
    @Column(name = "kod")
    private String zipCode;
    @Column(name = "stawka")
    private String rateRegular;
    @Column(name = "stawka_nadgodziny")
    private String rateOvertime;
    @Column(name = "urlop_pozostaly")
    private float numberDaysOffLeft;
    @Column(name = "wymiar_urlopu")
    private int numberDaysOffAnnually;
    @Column(name = "numer")
    private String telNumber;
    @Column(name = "inne")
    private String otherInfo;
    @Column(name = "email")
    private String email;
    @Column(name = "data_zatrudnienia")
    private LocalDate hiredDate;
    @Column(name = "data_zwolnienia")
    private LocalDate releaseDate;
    @Column(name = "data_nast_badania_lek")
    private LocalDate nextMedicalExaminationDate;
    @Column(name = "data_nast_szkolenia_bhp")
    private LocalDate nextBhpTrainingDate;
    @Column(name = "godz_mies")
    @Enumerated(EnumType.STRING)
    private RateType rateType;



}
