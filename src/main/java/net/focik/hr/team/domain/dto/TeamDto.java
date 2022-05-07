package net.focik.hr.team.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeamDto {

    private Integer idTeam;
    private String name;
    private List<Integer> idMembers;
}
