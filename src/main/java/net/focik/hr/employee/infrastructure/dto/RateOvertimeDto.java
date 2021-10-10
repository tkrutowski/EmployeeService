package net.focik.hr.employee.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stawka_nadgodziny")
@Getter
@ToString
public class RateOvertimeDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stawki_nadgodziny")
    @Setter Integer idRate;
    @Column(name = "id_pracownika")
    Integer idEmployee;
    @Column(name = "data_od")
    LocalDate dateFrom;
    @Column(name = "stawka_nadgodziny")
    BigDecimal rateValue;

}
