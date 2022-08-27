package net.focik.hr.employee.infrastructure.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dni_wolne")
@Getter
@ToString
@Builder
public class HolidaysDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dni_wolne")
    private Integer id;
    @Column(name = "data")
    private LocalDate date;
    @Column(name = "opis")
    private String description;
}

