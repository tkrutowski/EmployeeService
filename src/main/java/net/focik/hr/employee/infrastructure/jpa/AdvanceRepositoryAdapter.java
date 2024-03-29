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

    private final AdvanceDtoRepository advanceDtoRepository;
    private final JpaAdvanceMapper mapper;


    @Override
    public Optional<Advance> findById(Integer id) {
        Optional<AdvanceDto> byId = advanceDtoRepository.findById(id);
        if (byId.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.toDomain(byId.get()));
    }

    @Override
    public List<Advance> findByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        String dateFormat = date.getYear() + String.format("-%02d", date.getMonthValue());
        List<AdvanceDto> allByIdEmployeeAndDate = advanceDtoRepository.findAllByEmployeeAndDate(employeeId, dateFormat);
        return allByIdEmployeeAndDate.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Integer addAdvance(Advance advance) {
        return advanceDtoRepository.save(mapper.toDto(advance)).getId();
    }

    @Override
    public Integer updateAdvance(Advance advance) {
        return advanceDtoRepository.save(mapper.toDto(advance)).getId();
    }

    @Override
    public void deleteAdvanceById(Integer advanceId) {
advanceDtoRepository.deleteById(advanceId);
    }

}
