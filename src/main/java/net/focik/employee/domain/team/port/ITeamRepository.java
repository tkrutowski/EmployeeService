package net.focik.employee.domain.team.port;

import net.focik.employee.infrastructure.dto.TeamDbDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ITeamRepository {
    Integer add(TeamDbDto dto);

    Optional<TeamDbDto> findById(Integer id);

    List<TeamDbDto> findAll();

    List<TeamDbDto> findAllByIsActive(Boolean isActive);
}
