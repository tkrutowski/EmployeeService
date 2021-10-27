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
    //todo zmienić w bazie z bit na varchar
    @Enumerated(EnumType.STRING)
    @Column(name = "czy_splacone")
    private LoanStatus loanStatus;

}
