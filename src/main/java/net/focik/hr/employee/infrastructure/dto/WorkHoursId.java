package net.focik.hr.employee.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class WorkHoursId implements Serializable {

    @Column(name = "id_rok")
    private Integer idYear;
    @Column(name = "id_miesiac")
    private Integer idMonth;
}
