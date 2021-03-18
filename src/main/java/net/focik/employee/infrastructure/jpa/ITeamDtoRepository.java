package net.focik.employee.infrastructure.jpa;

import net.focik.employee.infrastructure.dto.TeamDto;
import org.springframework.data.jpa.repository.JpaRepository;

interface ITeamDtoRepository extends JpaRepository<TeamDto, Integer> {
}
