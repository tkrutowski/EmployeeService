package net.focik.hr.team.api.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeamDto {

    private Integer idTeam;
    private String name;
    Boolean isActive;
    private List<Integer> idMembers;
}
