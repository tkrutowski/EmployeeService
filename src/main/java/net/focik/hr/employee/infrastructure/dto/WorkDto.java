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
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "praca")
@Getter
@ToString
@Builder
public class WorkDto {

    @EmbeddedId
    private WorkId workId;
    @Column(name = "od_godz")
    private LocalTime startTime;
    @Column(name = "do_godz")
    private LocalTime stopTime;
}

