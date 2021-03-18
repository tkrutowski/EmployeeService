package net.focik.employee.domain.team;

import net.focik.employee.infrastructure.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface ITeamService {

    TeamDto getById(Integer id);

    List<TeamDto> getAll();

}
