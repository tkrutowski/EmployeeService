package net.focik.hr.team.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamFacade {
    private final TeamService teamService;

    public List<Team> getAllTeams() {
        return teamService.getAll();
    }

    public List<Team> getAllTeamsByIsActive(Boolean isActive) {
        return teamService.getAllByIsActive(isActive);
    }

    public Team getTeamById(Integer id) {
        return teamService.getById(id);
    }

    public List<Team> getAllTeamsWithMembers() {
        return teamService.getAllTeamsWithMembers();
    }
}
