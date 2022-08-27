package net.focik.hr.employee.infrastructure.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stawka_nadgodziny")
@Getter
@ToString
@Builder
public class RateOvertimeDbDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stawki_nadgodziny")
    private Integer idRate;
    @Column(name = "id_pracownika")
    private Integer idEmployee;
    @Column(name = "data_od")
    private LocalDate dateFrom;
    @Column(name = "stawka_nadgodziny")
    private BigDecimal rateValue;

}
