package net.focik.employee.domain.team;

import lombok.RequiredArgsConstructor;
import net.focik.employee.domain.team.exceptions.TeamDoesNotExistException;
import net.focik.employee.domain.team.port.ITeamRepository;
import net.focik.employee.infrastructure.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
class TeamServiceImpl implements ITeamService {

    private final ITeamRepository repository;

    @Override
    public TeamDto getById(Integer id) {
        Optional<TeamDto> byId = repository.findById(id);
        if(byId.isEmpty())
            throw new TeamDoesNotExistException(id);

        return byId.get();
    }

    @Override
    public List<TeamDto> getAll() {
        return repository.findAll();
    }
}
