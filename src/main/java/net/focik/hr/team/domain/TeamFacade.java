package net.focik.hr.team.domain;

import lombok.RequiredArgsConstructor;
import net.focik.hr.team.domain.dto.TeamDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamFacade {
    private final ITeamService teamService;

    public List<TeamDto> getAllTeams(){
       return teamService.getAll();
    }

    public List<TeamDto> getAllTeamsByIsActive(Boolean isActive){
       return teamService.getAllByIsActive(isActive);
    }

    public TeamDto getTeamById(Integer id){
        return teamService.getById(id);
    }

}
