package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.workTimeRecords.Work;
import net.focik.hr.employee.infrastructure.dto.WorkDto;
import net.focik.hr.employee.infrastructure.dto.WorkId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaWorkMapperTest {

    JpaWorkMapper mapper = new JpaWorkMapper();

    @Test
    void toDto() {
        //given
        Work work = new Work(1, LocalDate.of(2021, 12, 1), LocalTime.of(7,0), LocalTime.of(15,0));

        //when
        WorkDto result = mapper.toDto(work);

        //then
        assertEquals(work.getIdEmployee(), result.getWorkId().getIdEmployee());
        assertEquals(work.getDate(), result.getWorkId().getDate());
        assertEquals(work.getStartTime(), result.getStartTime());
        assertEquals(work.getStopTime(), result.getStopTime());
    }

    @Test
    void toDomain() {
        //given
        WorkDto dto = new WorkDto(new WorkId(1, LocalDate.of(2021, 12, 1)),  LocalTime.of(7,0), LocalTime.of(15,0));

        //when
        Work result = mapper.toDomain(dto);

        //then
        assertEquals(dto.getWorkId().getIdEmployee(), result.getIdEmployee());
        assertEquals(dto.getWorkId().getDate(), result.getDate());
        assertEquals(dto.getStartTime(), result.getStartTime());
        assertEquals(dto.getStopTime(), result.getStopTime());

    }

}