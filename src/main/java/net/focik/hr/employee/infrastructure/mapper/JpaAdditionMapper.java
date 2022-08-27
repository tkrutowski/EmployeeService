package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionType;
import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import net.focik.hr.employee.infrastructure.dto.AdditionTypeDbDto;
import org.springframework.stereotype.Component;

@Component
public class JpaAdditionMapper {

    public AdditionDto toDto(Addition addition) {
        return AdditionDto.builder()
                .id(addition.getId())
                .idEmployee(addition.getIdEmployee())
                .additionTypeDbDto(new AdditionTypeDbDto(addition.getAdditionTypeId(), addition.getAdditionTypeName()))
                .amount(addition.getAmount())
                .date(addition.getDate())
                .otherInfo(addition.getOtherInfo())
                .build();
    }

    public Addition toDomain(AdditionDto dto) {
        return Addition.builder()
                .id(dto.getId())
                .additionType(toDomain(dto.getAdditionTypeDbDto()))
                .amount(dto.getAmount())
                .date(dto.getDate())
                .otherInfo(dto.getOtherInfo())
                .build();
    }

    public AdditionType toDomain(AdditionTypeDbDto dto) {
        return new AdditionType(dto.getId(), dto.getName());
    }

    public AdditionTypeDbDto toDto(AdditionType additionType) {
        return new AdditionTypeDbDto(additionType.getId(), additionType.getName());
    }
}
