package net.focik.employee.domain.team;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.focik.employee.domain.team.dto.TeamDto;
import net.focik.employee.domain.team.exceptions.TeamDoesNotExistException;
import net.focik.employee.domain.team.port.ITeamRepository;
import net.focik.employee.infrastructure.dto.TeamDbDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
class TeamServiceImpl implements ITeamService {

    private final ITeamRepository repository;
    private ModelMapper mapper;

    @Override
    public TeamDto getById(Integer id) {
        Optional<TeamDbDto> byId = repository.findById(id);
        if(byId.isEmpty())
            throw new TeamDoesNotExistException(id);

        return mapper.map(byId.get(), TeamDto.class);
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

    private List<TeamDto> mapToDto(List<TeamDbDto> scopeDbDtoList) {
        List<TeamDto> teamDtos =  new ArrayList<>();

        for (TeamDbDto dbDto : scopeDbDtoList) {

            teamDtos.add(mapper.map(dbDto, TeamDto.class));
        }

        return teamDtos;
    }
}
