package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.workTimeRecords.Illness;
import net.focik.hr.employee.infrastructure.dto.IllnessDto;
import net.focik.hr.employee.infrastructure.dto.IllnessId;
import org.springframework.stereotype.Component;

@Component
public class JpaIllnessMapper {

    public IllnessDto toDto(Illness illness){
        IllnessDto illnessDto = IllnessDto.builder()
                .illnessId(new IllnessId(illness.getIdEmployee(), illness.getDate()))
                .illnessType(illness.getIllnessType())
                .build();
        return illnessDto;
    }

    public Illness toDomain(IllnessDto dto){
        Illness illness = new Illness(dto.getIllnessId().getIdEmployee(),dto.getIllnessId().getDate(),dto.getIllnessType());

        return illness;
    }
}
