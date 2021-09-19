package net.focik.hr.team.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import net.focik.hr.team.domain.port.ITeamRepository;
import net.focik.hr.team.infrastructure.dto.TeamDbDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeamRepositoryAdapter implements ITeamRepository {

    private final ITeamDtoRepository repository;
    @Override
    public Integer add(TeamDbDto dto) {
        return null;
    }

    @Override
    public Optional<TeamDbDto> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<TeamDbDto> findAll() {
        return repository.findAll();
    }

    @Override
    public List<TeamDbDto> findAllByIsActive(Boolean isActive) {
        return repository.findAllByIsActive(isActive);
    }
}
