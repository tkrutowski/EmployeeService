package net.focik.hr.team.domain;

import lombok.RequiredArgsConstructor;
import net.focik.hr.employee.domain.EmployeeQueryFacade;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import net.focik.hr.team.domain.exceptions.TeamNotFoundException;
import net.focik.hr.team.domain.port.ITeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class TeamService {

    private final EmployeeQueryFacade employeeQueryFacade;
    private final ITeamRepository repository;
    private final ModelMapper mapper;

    public Team getById(Integer id) {
        Optional<Team> byId = repository.findById(id);
        if (byId.isEmpty())
            throw new TeamNotFoundException(id);

        return byId.get();
    }

    public List<Team> getAllByIsActive(boolean isActive) {
        return repository.findAllByIsActive(isActive);
    }

    public List<Team> getAll() {
        return repository.findAll();
    }

    public List<Team> getAllTeamsWithMembers() {
        List<Team> teams = new ArrayList<>();
        List<EmployeeQueryBasicDto> allByEmploymentStatus = employeeQueryFacade.getAllEmployeesByEmploymentStatus(EmploymentStatus.HIRED);
        for (Team team : getAllByIsActive(true)) {
            team.setIdMembers(findTeamMembers(team, allByEmploymentStatus));
            teams.add(team);
        }
        return teams;
    }

    private List<Integer> findTeamMembers(Team team, List<EmployeeQueryBasicDto> allByEmploymentStatus) {
        return allByEmploymentStatus.stream()
                .filter(employeeQueryBasicDto -> team.getIdTeam().equals(employeeQueryBasicDto.getIdTeam()))
                .map(EmployeeQueryBasicDto::getId)
                .collect(Collectors.toList());
    }
}
