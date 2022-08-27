package net.focik.hr.team.infrastructure.dto;

import lombok.*;

import javax.persistence.*;

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
