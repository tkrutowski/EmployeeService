package net.focik.hr.employee.infrastructure.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dodatek")
@Getter
@ToString
@Builder
public class AdditionDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dodatku")
    private Integer id;
    @Column(name = "id_pracownika")
    private Integer idEmployee;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rodzaj_dodatku")
    private AdditionTypeDbDto additionTypeDbDto;
    @Column(name = "kwota")
    private BigDecimal amount;
    @Column(name = "data")
    private LocalDate date;
    @Column(name = "inne")
    private String otherInfo;

}
