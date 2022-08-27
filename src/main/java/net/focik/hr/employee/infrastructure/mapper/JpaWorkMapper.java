package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.worktimerecords.DayOff;
import net.focik.hr.employee.domain.worktimerecords.Illness;
import net.focik.hr.employee.domain.worktimerecords.Work;
import net.focik.hr.employee.infrastructure.dto.*;
import org.springframework.stereotype.Component;

@Component
public class JpaWorkMapper {

    public WorkDto toDto(Work work) {
        return WorkDto.builder()
                .workId(new WorkId(work.getIdEmployee(), work.getDate()))
                .startTime(work.getStartTime())
                .stopTime(work.getStopTime())
                .build();
    }

    public Work toDomain(WorkDto dto) {
        return new Work(dto.getWorkId().getIdEmployee(), dto.getWorkId().getDate(), dto.getStartTime(), dto.getStopTime());
    }

    public IllnessDto toDto(Illness illness) {
        return IllnessDto.builder()
                .illnessId(new IllnessId(illness.getIdEmployee(), illness.getDate()))
                .illnessType(illness.getIllnessType())
                .build();
    }

    public Illness toDomain(IllnessDto dto) {
        return new Illness(dto.getIllnessId().getIdEmployee(), dto.getIllnessId().getDate(), dto.getIllnessType());
    }

    public DayOffDto toDto(DayOff dayOff) {
        return DayOffDto.builder()
                .dayOffId(new DayOffId(dayOff.getIdEmployee(), dayOff.getDate()))
                .dayOffType(dayOff.getDayOffType())
                .build();
    }

    public DayOff toDomain(DayOffDto dto) {
        return new DayOff(dto.getDayOffId().getIdEmployee(), dto.getDayOffId().getDate(), dto.getDayOffType());
    }
}
