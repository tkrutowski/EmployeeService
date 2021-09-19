package net.focik.employee.domain.team;


import net.focik.employee.domain.team.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
interface ITeamService {

    TeamDto getById(Integer id);

    List<TeamDto> getAllByIsActive(boolean isActive);

    List<TeamDto> getAll();



}
