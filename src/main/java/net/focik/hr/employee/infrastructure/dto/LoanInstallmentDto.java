package net.focik.hr.employee.infrastructure.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rata_pozyczki")
@Getter
@ToString
@Builder
public class LoanInstallmentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_raty")
    private Integer id;
    @Column(name = "id_pozyczki")
    private Integer idLoan;
    @Column(name = "kwota_raty")
    private BigDecimal amount;
    @Column(name = "data_raty")
    private LocalDate date;
    @Column(name = "wplata_wlasna")
    private Boolean isOwnRepayment;

}
