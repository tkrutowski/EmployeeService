package net.focik.hr.employee.infrastructure.jpa;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.addition.AdditionType;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionTypeRepository;
import net.focik.hr.employee.infrastructure.dto.AdditionTypeDbDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
@AllArgsConstructor
class AdditionTypeRepositoryAdapter implements AdditionTypeRepository {

    AdditionTypeDtoRepository additionTypeDtoRepository;
    ModelMapper mapper;

    @Override
    public List<AdditionType> findAll() {
        return additionTypeDtoRepository.findAll().stream()
                .map(additionTypeDbDto -> mapper.map(additionTypeDbDto, AdditionType.class))
                .collect(Collectors.toList());
    }

    @Override
    public Integer add(AdditionType additionType) {
        AdditionTypeDbDto dbDto = mapper.map(additionType, AdditionTypeDbDto.class);
        return additionTypeDtoRepository.save(dbDto).getId();
    }
}
