package net.focik.hr.team.application;

import lombok.RequiredArgsConstructor;
import net.focik.hr.team.domain.TeamFacade;
import net.focik.hr.team.domain.dto.TeamDto;
import net.focik.hr.team.domain.port.primary.IGetTeamUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService implements IGetTeamUseCase {
    private final TeamFacade facade;

    @Override
    public List<TeamDto> getAllTeams() {
        return facade.getAllTeams();
    }

    @Override
    public List<TeamDto> getAllTeamsByIsActive(Boolean isActive) {
        return facade.getAllTeamsByIsActive(isActive);
    }

    @Override
    public List<TeamDto> getAllTeamsWithMembers() {
        return facade.getAllTeamsWithMembers();
    }

    @Override
    public TeamDto getTeamById(Integer id) {
        return facade.getTeamById(id);
    }
}
