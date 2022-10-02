package net.focik.hr.employee.query;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LoanInstallment_view")
@Getter
@ToString
@Builder
public class LoanInstallmentQueryDbDto {
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
    @Column(name = "nazwa")
    private String loanName;
}