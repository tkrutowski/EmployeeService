package net.focik.employee.infrastructure.jpa;

import net.focik.employee.infrastructure.dto.TeamDbDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ITeamDtoRepository extends JpaRepository<TeamDbDto, Integer> {

    List<TeamDbDto> findAllByIsActive(Boolean isActive);
}
