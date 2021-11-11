package net.focik.hr.employee.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class IllnessId implements Serializable {

    @Column(name = "id_pracownika")
    private Integer idEmployee;
    @Column(name = "data")
    private LocalDate date;
}
