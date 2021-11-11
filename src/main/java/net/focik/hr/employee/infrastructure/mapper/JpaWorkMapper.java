package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.workTimeRecords.Work;
import net.focik.hr.employee.infrastructure.dto.WorkDto;
import net.focik.hr.employee.infrastructure.dto.WorkId;
import org.springframework.stereotype.Component;

@Component
public class JpaWorkMapper {

    public WorkDto toDto(Work work){
        WorkDto workDto = WorkDto.builder()
                .workId(new WorkId(work.getIdEmployee(), work.getDate()))
                .startTime(work.getStartTime())
                .stopTime(work.getStopTime())
                .build();
        return workDto;
    }

    public Work toDomain(WorkDto dto){
        Work work = new Work(dto.getWorkId().getIdEmployee(),dto.getWorkId().getDate(), dto.getStartTime(), dto.getStopTime());

        return work;
    }
}
