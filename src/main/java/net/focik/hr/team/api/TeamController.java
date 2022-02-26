package net.focik.hr.team.api;

import lombok.AllArgsConstructor;
import net.focik.hr.team.domain.TeamFacade;
import net.focik.hr.team.domain.dto.TeamDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @GetMapping
    ResponseEntity<List<TeamDto>> getTeams() {
        int i=0;
        List<TeamDto> teamDtoList =facade.getAllTeams();
        return new ResponseEntity<>(teamDtoList, HttpStatus.OK);
    }

//    @CrossOrigin
    @GetMapping("/active")
    ResponseEntity<List<TeamDto>> getScopeGasConnection(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive) {
        int i = 0;
        List<TeamDto> teamDtoList =facade.getAllTeamsByIsActive(isActive);
        return new ResponseEntity<>(teamDtoList, HttpStatus.OK);
    }

}
