package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceQueryRepository;
import net.focik.hr.employee.infrastructure.dto.JpaMapper;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAdvances;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component()
@Profile({"test"})
@Log
public class InMemoryAdvanceQueryRepositoryAdapter implements AdvanceQueryRepository {

    JpaMapper jpaMapper = new JpaMapper();

    @Override
    public Optional<Advance> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Advance> findByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        return DataBaseAdvances.getAdvancesHashMap()
                .entrySet()
                .stream()
                .map(integerAdvanceDtoEntry -> integerAdvanceDtoEntry.getValue())
                .filter(advanceDto -> advanceDto.getIdEmployee().equals(employeeId))
                .filter(advanceDto -> advanceDto.getDate().getYear() == date.getYear())
                .filter(advanceDto -> advanceDto.getDate().getMonth() == date.getMonth())
                .map(advanceDto -> jpaMapper.toDomain(advanceDto))
                .collect(Collectors.toList());
    }
}
