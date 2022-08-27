package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.infrastructure.dto.AdvanceDto;
import org.springframework.stereotype.Component;

@Component
public class JpaAdvanceMapper {

    public AdvanceDto toDto(Advance advance) {
        return AdvanceDto.builder()
                .id(advance.getId())
                .idEmployee(advance.getIdEmployee())
                .amount(advance.getAmount())
                .date(advance.getDate())
                .otherInfo(advance.getOtherInfo())
                .build();
    }

    public Advance toDomain(AdvanceDto dto) {
        return Advance.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .otherInfo(dto.getOtherInfo())
                .build();
    }
}
