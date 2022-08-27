package net.focik.hr.team.application;

import lombok.RequiredArgsConstructor;
import net.focik.hr.team.domain.Team;
import net.focik.hr.team.domain.TeamFacade;
import net.focik.hr.team.domain.port.primary.IGetTeamUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamApiService implements IGetTeamUseCase {
    private final TeamFacade facade;

    @Override
    public List<Team> getAllTeams() {
        return facade.getAllTeams();
    }

    @Override
    public List<Team> getAllTeamsByIsActive(Boolean isActive) {
        return facade.getAllTeamsByIsActive(isActive);
    }

    @Override
    public List<Team> getAllTeamsWithMembers() {
        return facade.getAllTeamsWithMembers();
    }

    @Override
    public Team getTeamById(Integer id) {
        return facade.getTeamById(id);
    }
}
