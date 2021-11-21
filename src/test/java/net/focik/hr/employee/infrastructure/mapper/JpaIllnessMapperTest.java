package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.workTimeRecords.Illness;
import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;
import net.focik.hr.employee.infrastructure.dto.IllnessDto;
import net.focik.hr.employee.infrastructure.dto.IllnessId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JpaIllnessMapperTest {

    JpaWorkMapper mapper = new JpaWorkMapper();

    @Test
    void toDto() {
        //given
        Illness illness = new Illness(1, LocalDate.of(2021, 12, 1), IllnessType.ILLNESS_100);

        //when
        IllnessDto result = mapper.toDto(illness);

        //then
        assertEquals(illness.getIdEmployee(), result.getIllnessId().getIdEmployee());
        assertEquals(illness.getDate(), result.getIllnessId().getDate());
        assertEquals(illness.getIllnessType(), result.getIllnessType());
    }

    @Test
    void toDomain() {
        //given
        IllnessDto dto = new IllnessDto(new IllnessId(1, LocalDate.of(2021, 12, 1)), IllnessType.ILLNESS_100);

        //when
        Illness result = mapper.toDomain(dto);

        //then
        assertEquals(dto.getIllnessId().getIdEmployee(), result.getIdEmployee());
        assertEquals(dto.getIllnessId().getDate(), result.getDate());
        assertEquals(dto.getIllnessType(), result.getIllnessType());

    }
}