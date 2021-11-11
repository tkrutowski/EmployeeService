package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionRepository;
import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAddition;
import net.focik.hr.employee.infrastructure.mapper.JpaAdditionMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component()
@Profile({"test"})
@Log
public class InMemoryAdditionRepositoryAdapter implements AdditionRepository {


    JpaAdditionMapper jpaMapper = new JpaAdditionMapper();

    @Override
    public Optional<Addition> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Addition> findByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        return DataBaseAddition.getAdditionsHashMap()
                .entrySet()
                .stream()
                .map(integerAdvanceDtoEntry -> integerAdvanceDtoEntry.getValue())
                .filter(additionDto -> additionDto.getIdEmployee().equals(employeeId))
                .filter(additionDto -> additionDto.getDate().getYear() == date.getYear())
                .filter(additionDto -> additionDto.getDate().getMonth() == date.getMonth())
                .map(additionDto -> jpaMapper.toDomain(additionDto, DataBaseAddition.getAdditionTypesHashMap().entrySet().stream()
                        .filter(e -> additionDto.getIdAdditionType().equals(e.getKey()))
                        .map(Map.Entry::getValue)
                        .findFirst()
                        .orElse("nie znaleziono")))
                .collect(Collectors.toList());
    }
    @Override
    public Integer add(Addition addition) {
        AdditionDto additionDto = jpaMapper.toDto(addition);
        log.info("Try add into inMemoryDb addition: " + additionDto.toString());
        if (additionDto == null)
            throw new NullPointerException("Addition cannot be null");
        Integer id = DataBaseAddition.getAdditionsHashMap().size() + 1;
        // employee.setId(id);
        DataBaseAddition.getAdditionsHashMap().put(id, additionDto);
        log.info("Succssec id = " + id);
        return id;
    }
}
