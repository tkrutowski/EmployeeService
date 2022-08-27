package net.focik.hr.employee.infrastructure.dto;

import lombok.*;
import net.focik.hr.employee.domain.share.LoanStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pozyczka")
@Getter
@ToString
@Builder
public class LoanDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pozyczki")
    private Integer id;
    @Column(name = "id_pracownika")
    private Integer idEmployee;
    @Column(name = "nazwa")
    private String name;
    @Column(name = "kwota")
    private BigDecimal amount;
    @Column(name = "data")
    private LocalDate date;
    @Column(name = "ile_pobierac")
    private BigDecimal installmentAmount;
    @Column(name = "inne")
    private String otherInfo;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LoanStatus loanStatus;

}
