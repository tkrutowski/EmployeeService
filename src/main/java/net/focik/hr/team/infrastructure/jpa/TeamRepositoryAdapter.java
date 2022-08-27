package net.focik.hr.team.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import net.focik.hr.team.domain.Team;
import net.focik.hr.team.domain.port.ITeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeamRepositoryAdapter implements ITeamRepository {

    private final ITeamDtoRepository repository;
    private final ModelMapper mapper;

    @Override
    public Integer add(Team dto) {
        return null;
    }

    @Override
    public Optional<Team> findById(Integer id) {
        return Optional.of(mapper.map(repository.findById(id), Team.class));
    }

    @Override
    public List<Team> findAll() {

        return repository.findAll().stream()
                .map(teamDbDto -> mapper.map(teamDbDto, Team.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Team> findAllByIsActive(Boolean isActive) {
        return repository.findAllByIsActive(isActive).stream()
                .map(teamDbDto -> mapper.map(teamDbDto, Team.class))
                .collect(Collectors.toList());
    }
}
