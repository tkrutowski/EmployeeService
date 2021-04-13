package net.focik.employee.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teams")
@ToString
public class TeamDbDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_team")
    private Integer idTeam;
    private String name;
    private Boolean isActive;
}
