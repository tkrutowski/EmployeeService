package net.focik.hr.team.api;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.domain.utils.PrivilegeHelper;
import net.focik.hr.team.domain.dto.TeamDto;
import net.focik.hr.team.domain.exceptions.AccessDeniedException;
import net.focik.hr.team.domain.exceptions.ExceptionHandling;
import net.focik.hr.team.domain.port.primary.IGetTeamUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.focik.hr.employee.domain.utils.PrivilegeConstant.AUTHORITIES;

@Log4j2
//@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
class TeamController extends ExceptionHandling {

    private final IGetTeamUseCase getTeamUseCase;




//    @GetMapping
//    ResponseEntity<List<TeamDto>> getTeams(@RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
//        int i=0;
////        final List<String> accessRole = List.of("TASK_CALENDAR_READ_ALL","TASK_CALENDAR_READ");
////
////        //Predicate<String> hasRole = searchedRole -> Arrays.stream(roles).anyMatch(role -> searchedRole.equals(role));
//////        if(hasRole.test(accessRole.get(0)))
////  //          log.info("Has role: "+accessRole.get(0));
////
////
////        if(!hasAccess(List.of(roles), accessRole)){
////         throw new AccessDeniedException();
////        }
//
//        List<TeamDto> teamDtoList =getTeamUseCase.getAllTeams();
//        return new ResponseEntity<>(teamDtoList, HttpStatus.OK);
//    }

    @GetMapping
    ResponseEntity<List<TeamDto>> getTeamsWithMembers(@RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        int i=0;
        final List<String> accessRole = List.of("TASK_CALENDAR_READ_ALL","TASK_CALENDAR_READ");

        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
         throw new AccessDeniedException();
        }

        List<TeamDto> teamDtoList =getTeamUseCase.getAllTeamsWithMembers();
        return new ResponseEntity<>(teamDtoList, HttpStatus.OK);
    }

    @GetMapping("/active")
    ResponseEntity<List<TeamDto>> getScopeGasConnection(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive) {
        int i = 0;
        List<TeamDto> teamDtoList =getTeamUseCase.getAllTeamsByIsActive(isActive);
        return new ResponseEntity<>(teamDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<TeamDto> getTeam(@PathVariable(name = "id") int idTeam, @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        int i=0;
        final List<String> accessRole = List.of("TASK_CALENDAR_READ_ALL","TASK_CALENDAR_READ");

        if(!PrivilegeHelper.checkAccess(List.of(roles), accessRole)){
            throw new AccessDeniedException();
        }

        TeamDto teamById = getTeamUseCase.getTeamById(idTeam);
        return new ResponseEntity<>(teamById, HttpStatus.OK);
    }
}
