package net.focik.employee.api;

import lombok.AllArgsConstructor;
import net.focik.employee.domain.team.TeamFacade;
import net.focik.employee.infrastructure.dto.TeamDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teams")
class TeamController {

    private TeamFacade facade;

    @GetMapping("/{id}")
    TeamDto getTeam(@PathVariable Integer id) {
        int i=0;
        return facade.getTeamById(id);
    }

    @GetMapping
    List<TeamDto> getTeams() {
        int i=0;
        return facade.getAllTeams();
    }

}
