package net.focik.employee.domain.team;

import lombok.RequiredArgsConstructor;
import net.focik.employee.infrastructure.dto.TeamDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamFacade {
    private final ITeamService teamService;

    public List<TeamDto> getAllTeams(){
       return teamService.getAll();
    }

    public TeamDto getTeamById(Integer id){
        return teamService.getById(id);
    }
}
