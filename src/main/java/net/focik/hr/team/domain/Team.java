package net.focik.hr.team.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team {

    Integer idTeam;
    String name;
    Boolean isActive;
    private List<Integer> idMembers;
}
