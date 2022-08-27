package net.focik.hr.employee.infrastructure.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class WorkId implements Serializable {
    @NonNull
    @Column(name = "id_pracownika")
    private Integer idEmployee;
    @NonNull
    @Column(name = "data")
    private LocalDate date;
}
