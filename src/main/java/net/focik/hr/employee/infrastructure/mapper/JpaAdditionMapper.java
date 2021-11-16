package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionType;
import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import net.focik.hr.employee.infrastructure.dto.AdditionTypeDto;
import org.springframework.stereotype.Component;

@Component
public class JpaAdditionMapper {

    public AdditionDto toDto(Addition addition){
        AdditionDto advanceDto = AdditionDto.builder()
                .id(addition.getId())
                .idEmployee(addition.getIdEmployee())
                .additionTypeDto(new AdditionTypeDto(addition.getAdditionTypeId(), addition.getAdditionTypeName()))
                .amount(addition.getAmount())
                .date(addition.getDate())
                .otherInfo(addition.getOtherInfo())
                .build();
        return advanceDto;
    }

    public Addition toDomain(AdditionDto dto){
        Addition addition = Addition.builder()
                .id(dto.getId())
                .additionType(toDomain(dto.getAdditionTypeDto()))
                .amount(dto.getAmount())
                .date(dto.getDate())
                .otherInfo(dto.getOtherInfo())
                .build();

        return addition;
    }

    public AdditionType toDomain(AdditionTypeDto dto){
        return  new AdditionType(dto.getId(), dto.getName());
    }
    public AdditionTypeDto toDto(AdditionType additionType){
        return  new AdditionTypeDto(additionType.getId(), additionType.getName());
    }
}
