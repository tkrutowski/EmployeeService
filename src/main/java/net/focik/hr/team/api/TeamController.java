package net.focik.hr.team.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.team.api.dto.TeamDto;
import net.focik.hr.team.domain.Team;
import net.focik.hr.team.domain.port.primary.IGetTeamUseCase;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.ExceptionHandling;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static net.focik.hr.utils.privileges.PrivilegeHelper.*;

@Log4j2
//@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
class TeamController extends ExceptionHandling {

    private final IGetTeamUseCase getTeamUseCase;
    private final ModelMapper mapper;

    @GetMapping
    ResponseEntity<List<TeamDto>> getTeamsWithMembers(@RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        final List<String> accessRole = List.of(ROLE_ADMIN, TASK_CALENDAR_READ_ALL, TASK_CALENDAR_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<TeamDto> teamDtoList = getTeamUseCase.getAllTeamsWithMembers().stream()
                .map(team -> mapper.map(team, TeamDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(teamDtoList, HttpStatus.OK);
    }

    @GetMapping("/active")
    ResponseEntity<List<TeamDto>> getActiveTeams(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive,
                                                 @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        final List<String> accessRole = List.of(ROLE_ADMIN, TASK_CALENDAR_READ_ALL, TASK_CALENDAR_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<TeamDto> teamDtoList = getTeamUseCase.getAllTeamsByIsActive(isActive).stream()
                .map(team -> mapper.map(team, TeamDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(teamDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<TeamDto> getTeam(@PathVariable(name = "id") int idTeam,
                                    @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        final List<String> accessRole = List.of(ROLE_ADMIN, TASK_CALENDAR_READ_ALL, TASK_CALENDAR_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Team teamById = getTeamUseCase.getTeamById(idTeam);
        return new ResponseEntity<>(mapper.map(teamById, TeamDto.class), HttpStatus.OK);
    }
}
