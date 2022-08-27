package net.focik.hr.employee.infrastructure.dto;

import lombok.*;
import net.focik.hr.employee.domain.share.RateType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stawka")
@Getter
@ToString
@Builder
public class RateRegularDbDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stawki")
    @Setter
    Integer idRate;
    @Column(name = "id_pracownika")
    private Integer idEmployee;
    @Column(name = "godz_mies")
    @Enumerated(EnumType.STRING)
    private RateType rateType;
    @Column(name = "data_od")
    private LocalDate dateFrom;
    @Column(name = "stawka")
    private BigDecimal rateValue;

}
