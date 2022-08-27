package net.focik.hr.team.domain.port;

import net.focik.hr.team.domain.Team;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ITeamRepository {
    Integer add(Team dto);

    Optional<Team> findById(Integer id);

    List<Team> findAll();

    List<Team> findAllByIsActive(Boolean isActive);
}
