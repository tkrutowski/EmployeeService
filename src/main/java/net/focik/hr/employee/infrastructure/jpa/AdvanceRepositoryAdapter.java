package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceRepository;
import net.focik.hr.employee.infrastructure.dto.AdvanceDto;
import net.focik.hr.employee.infrastructure.mapper.JpaAdvanceMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Primary
@AllArgsConstructor
class AdvanceRepositoryAdapter implements AdvanceRepository {

    AdvanceDtoRepository advanceDtoRepository;
    JpaAdvanceMapper mapper;


    @Override
    public Optional<Advance> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Advance> findByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        List<AdvanceDto> allByIdEmployeeAndDate = advanceDtoRepository.findAllByIdEmployeeAndDate(employeeId, date);
        return null;
    }

    @Override
    public Integer add(Advance advance) {
        return null;
    }

}
