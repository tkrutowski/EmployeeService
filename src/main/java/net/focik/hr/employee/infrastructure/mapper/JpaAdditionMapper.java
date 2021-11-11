package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionType;
import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import org.springframework.stereotype.Component;

@Component
public class JpaAdditionMapper {

    public AdditionDto toDto(Addition addition, Integer idEmployee){
        AdditionDto advanceDto = AdditionDto.builder()
                .id(addition.getId())
                .idEmployee(idEmployee)
                .idAdditionType(addition.getAdditionTypeId())
                .amount(addition.getAmount())
                .date(addition.getDate())
                .otherInfo(addition.getOtherInfo())
                .build();
        return advanceDto;
    }

    public Addition toDomain(AdditionDto dto, String additionName){
        Addition addition = Addition.builder()
                .id(dto.getId())
                .additionType(new AdditionType(dto.getIdAdditionType(),additionName))
                .amount(dto.getAmount())
                .date(dto.getDate())
                .otherInfo(dto.getOtherInfo())
                .build();

        return addition;
    }
}
