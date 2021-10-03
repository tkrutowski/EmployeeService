package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stawka")
@Getter
@ToString
public class RateRegular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stawki")
    @Setter Integer idRate;
    @Column(name = "id_pracownika")
    Integer idEmployee;
    @Column(name = "godz_mies")
    @Enumerated(EnumType.STRING)
    private RateType rateType;
    @Column(name = "data_od")
    LocalDate dateFrom;
    @Column(name = "stawka")
    BigDecimal rateValue;

}
