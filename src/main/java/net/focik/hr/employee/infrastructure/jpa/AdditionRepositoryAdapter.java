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
        String dateFormat = date.getYear() + String.format("-%02d", date.getMonthValue());
        List<AdditionDto> allByIdEmployeeAndDate = additionDtoRepository.findAllByIdEmployeeAndDate(employeeId, dateFormat);
        return allByIdEmployeeAndDate.stream()
                .map(additionDto -> mapper.toDomain(additionDto))
                .collect(Collectors.toList());
    }

    @Override
    public Integer addAddition(Addition addition) {
        return additionDtoRepository.save(mapper.toDto(addition)).getId();
    }

    @Override
    public Integer updateAddition(Addition addition) {
        return additionDtoRepository.save(mapper.toDto(addition)).getId();
    }

    @Override
    public void deleteAddition(Integer additionId) {
        additionDtoRepository.deleteById(additionId);
    }

}
