package net.focik.hr.employee.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.focik.hr.employee.domain.share.LoanStatus;

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
