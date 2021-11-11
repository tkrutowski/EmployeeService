package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.domain.advance.port.secondary.AdvanceRepository;
import net.focik.hr.employee.infrastructure.dto.AdvanceDto;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAdvances;
import net.focik.hr.employee.infrastructure.mapper.JpaAdvanceMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile({"test"})
@Log
public class InMemoryAdvanceRepositoryAdapter implements AdvanceRepository {

    JpaAdvanceMapper jpaMapper = new JpaAdvanceMapper();

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
    @Override
    public Integer add(Advance advance, Integer idEmployee) {
        AdvanceDto advanceDto = jpaMapper.toDto(advance, idEmployee);
        log.info("Try add into inMemoryDb advance: " + advanceDto.toString());
        if (advanceDto == null)
            throw new NullPointerException("Advance cannot be null");
        Integer id = DataBaseAdvances.getAdvancesHashMap().size() + 1;
        // employee.setId(id);
        DataBaseAdvances.getAdvancesHashMap().put(id, advanceDto);
        log.info("Succssec id = " + id);
        return id;
    }
}
