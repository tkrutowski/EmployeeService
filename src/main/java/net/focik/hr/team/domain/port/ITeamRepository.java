package net.focik.hr.team.domain.port;

import net.focik.hr.team.infrastructure.dto.TeamDbDto;
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
