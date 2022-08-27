package net.focik.hr.employee.infrastructure.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "zaliczka")
@Getter
@ToString
@Builder
public class AdvanceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zaliczki")
    private Integer id;
    @Column(name = "id_pracownika")
    private Integer idEmployee;
    @Column(name = "kwota")
    private BigDecimal amount;
    @Column(name = "data")
    private LocalDate date;
    @Column(name = "inne")
    private String otherInfo;

}
