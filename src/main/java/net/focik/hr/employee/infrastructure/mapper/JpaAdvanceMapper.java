package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.infrastructure.dto.AdvanceDto;
import org.springframework.stereotype.Component;

@Component
public class JpaAdvanceMapper {

    public AdvanceDto toDto(Advance advance, Integer idEmployee){
        AdvanceDto advanceDto = AdvanceDto.builder()
                .id(advance.getId())
                .idEmployee(idEmployee)
                .amount(advance.getAmount())
                .date(advance.getDate())
                .otherInfo(advance.getOtherInfo())
                .build();
        return advanceDto;
    }

    public Advance toDomain (AdvanceDto dto){
        Advance advance = Advance.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .otherInfo(dto.getOtherInfo())
                .build();

        return advance;
    }
}
