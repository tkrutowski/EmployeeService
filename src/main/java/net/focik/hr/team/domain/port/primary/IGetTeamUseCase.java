package net.focik.hr.team.domain.port.primary;

import net.focik.hr.team.domain.dto.TeamDto;

import java.util.List;

public interface IGetTeamUseCase {
    List<TeamDto> getAllTeams();
    List<TeamDto> getAllTeamsByIsActive(Boolean isActive);
    List<TeamDto> getAllTeamsWithMembers();
    TeamDto getTeamById(Integer id);
}
