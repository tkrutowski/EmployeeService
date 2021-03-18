package net.focik.employee.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import net.focik.employee.domain.team.port.ITeamRepository;
import net.focik.employee.infrastructure.dto.TeamDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeamRepositoryAdapter implements ITeamRepository {

    private final ITeamDtoRepository repository;
    @Override
    public Integer add(TeamDto dto) {
        return null;
    }

    @Override
    public Optional<TeamDto> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<TeamDto> findAll() {
        return repository.findAll();
    }
}
