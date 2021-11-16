package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionRepository;
import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import net.focik.hr.employee.infrastructure.mapper.JpaAdditionMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
@AllArgsConstructor
class AdditionRepositoryAdapter implements AdditionRepository {

    AdditionDtoRepository additionDtoRepository;
    JpaAdditionMapper mapper;

    @Override
    public Optional<Addition> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Addition> findByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        List<AdditionDto> allByIdEmployeeAndDate = additionDtoRepository.findAllByIdEmployeeAndDate(employeeId, date.getYear(), date.getMonth().getValue());
        return allByIdEmployeeAndDate.stream()
                .map(additionDto -> mapper.toDomain(additionDto))
                .collect(Collectors.toList());
    }

    @Override
    public Integer add(Addition advance) {
        return null;
    }

}
