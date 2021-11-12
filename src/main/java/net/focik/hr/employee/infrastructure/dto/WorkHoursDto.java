package net.focik.hr.employee.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "godziny_robocze")
@Getter
@ToString
@Builder
public class WorkHoursDto {

    @EmbeddedId
    private WorkHoursId workHoursId;
    @Column(name = "ilosc_godzin")
    private Integer numberOfHours;
}

