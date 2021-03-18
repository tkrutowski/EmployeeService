package net.focik.employee.domain.team.port;

import net.focik.employee.infrastructure.dto.TeamDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ITeamRepository {
    Integer add(TeamDto dto);

    Optional<TeamDto> findById(Integer id);

    List<TeamDto> findAll();
}
