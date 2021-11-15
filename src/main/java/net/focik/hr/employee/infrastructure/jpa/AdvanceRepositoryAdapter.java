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
import java.util.stream.Collectors;

@Component
@Primary
@AllArgsConstructor
class AdvanceRepositoryAdapter implements AdvanceRepository {

    AdvanceDtoRepository advanceDtoRepository;
    JpaAdvanceMapper mapper;


    @Override
    public Optional<Advance> findById(Integer id) {
        Optional<AdvanceDto> byId = advanceDtoRepository.findById(id);
        if (byId.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.toDomain(byId.get()));
    }

    @Override
    public List<Advance> findByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        List<AdvanceDto> allByIdEmployeeAndDate = advanceDtoRepository.findAllByIdEmployeeAndDate(employeeId, date.getYear(), date.getMonth().getValue());
        return allByIdEmployeeAndDate.stream()
                .map(advanceDto -> mapper.toDomain(advanceDto))
                .collect(Collectors.toList());
    }

    @Override
    public Integer add(Advance advance) {
        return null;
    }

}
