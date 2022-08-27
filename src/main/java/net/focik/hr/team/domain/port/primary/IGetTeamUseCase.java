package net.focik.hr.team.domain.port.primary;

import net.focik.hr.team.domain.Team;

import java.util.List;

public interface IGetTeamUseCase {
    List<Team> getAllTeams();

    List<Team> getAllTeamsByIsActive(Boolean isActive);

    List<Team> getAllTeamsWithMembers();

    Team getTeamById(Integer id);
}
