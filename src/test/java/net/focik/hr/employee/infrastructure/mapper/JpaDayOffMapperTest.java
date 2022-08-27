package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.worktimerecords.DayOff;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;
import net.focik.hr.employee.infrastructure.dto.DayOffDto;
import net.focik.hr.employee.infrastructure.dto.DayOffId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaDayOffMapperTest {


    JpaWorkMapper mapper = new JpaWorkMapper();

    @Test
    void toDto() {
        //given
        DayOff dayOff = new DayOff(1, LocalDate.of(2021, 12, 1), DayOffType.REST);

        //when
        DayOffDto result = mapper.toDto(dayOff);

        //then
        assertEquals(dayOff.getIdEmployee(), result.getDayOffId().getIdEmployee());
        assertEquals(dayOff.getDate(), result.getDayOffId().getDate());
        assertEquals(dayOff.getDayOffType(), result.getDayOffType());
    }

    @Test
    void toDomain() {
        //given
        DayOffDto dto = new DayOffDto(new DayOffId(1, LocalDate.of(2021, 12, 1)), DayOffType.REST);

        //when
        DayOff result = mapper.toDomain(dto);

        //then
        assertEquals(dto.getDayOffId().getIdEmployee(), result.getIdEmployee());
        assertEquals(dto.getDayOffId().getDate(), result.getDate());
        assertEquals(dto.getDayOffType(), result.getDayOffType());

    }
}