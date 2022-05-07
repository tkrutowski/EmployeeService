package net.focik.hr.team.domain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import net.focik.hr.team.domain.dto.TeamDto;
import net.focik.hr.team.domain.exceptions.TeamNotFoundException;
import net.focik.hr.team.domain.port.ITeamRepository;
import net.focik.hr.team.infrastructure.dto.TeamDbDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
class TeamServiceImpl implements ITeamService {

    private final EmployeeQueryRepository employeeQueryRepository;
    private final ITeamRepository repository;
    private final ModelMapper mapper;

    @Override
    public TeamDto getById(Integer id) {
        Optional<TeamDbDto> byId = repository.findById(id);
        if(byId.isEmpty())
            throw new TeamNotFoundException(id);

        TeamDto teamDto = mapper.map(byId.get(), TeamDto.class);
        findMembers(teamDto);
        return teamDto;
    }

    @Override
    public List<TeamDto> getAllByIsActive(boolean isActive) {
        List<TeamDbDto> allByIsActive = repository.findAllByIsActive(isActive);

        return mapToDto(allByIsActive);
    }

    @Override
    public List<TeamDto> getAll(){
        List<TeamDbDto> all = repository.findAll();

        return mapToDto(all);
    }

    @Override
    public List<TeamDto> getAllTeamsWithMembers() {
//        List<TeamDto> teamDtos teamDtos =  new ArrayList<>();
        List<TeamDto> activeTeams = mapToDto(repository.findAllByIsActive(true));

        for (TeamDto t: activeTeams) {
            findMembers(t);
        }

        return activeTeams;
    }

    private void findMembers(TeamDto teamDto) {
        List<EmployeeQueryBasicDto> allByEmploymentStatus = employeeQueryRepository.findAllByEmploymentStatus(EmploymentStatus.HIRED);
        teamDto.setIdMembers(allByEmploymentStatus.stream()
                    .filter(employeeQueryBasicDto -> teamDto.getIdTeam().equals(employeeQueryBasicDto.getIdTeam()))
                    .map(employeeQueryBasicDto -> employeeQueryBasicDto.getId())
                    .collect(Collectors.toList()));
    }

    private List<TeamDto> mapToDto(List<TeamDbDto> scopeDbDtoList) {
        List<TeamDto> teamDtos =  new ArrayList<>();

        for (TeamDbDto dbDto : scopeDbDtoList) {

            teamDtos.add(mapper.map(dbDto, TeamDto.class));
        }

        return teamDtos;
    }
}
