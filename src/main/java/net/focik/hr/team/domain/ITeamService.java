package net.focik.hr.team.domain;


import net.focik.hr.team.domain.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface ITeamService {

    TeamDto getById(Integer id);

    List<TeamDto> getAllByIsActive(boolean isActive);

    List<TeamDto> getAll();


    List<TeamDto> getAllTeamsWithMembers();
}
