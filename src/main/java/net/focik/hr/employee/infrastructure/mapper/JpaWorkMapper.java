package net.focik.hr.employee.infrastructure.mapper;

import net.focik.hr.employee.domain.workTimeRecords.DayOff;
import net.focik.hr.employee.domain.workTimeRecords.Illness;
import net.focik.hr.employee.domain.workTimeRecords.Work;
import net.focik.hr.employee.infrastructure.dto.DayOffDto;
import net.focik.hr.employee.infrastructure.dto.DayOffId;
import net.focik.hr.employee.infrastructure.dto.IllnessDto;
import net.focik.hr.employee.infrastructure.dto.IllnessId;
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

    public DayOffDto toDto(DayOff dayOff){
        DayOffDto dayOffDto = DayOffDto.builder()
                .dayOffId(new DayOffId(dayOff.getIdEmployee(), dayOff.getDate()))
                .dayOffType(dayOff.getDayOffType())
                .build();
        return dayOffDto;
    }

    public DayOff toDomain(DayOffDto dto){
        DayOff dayOff = new DayOff(dto.getDayOffId().getIdEmployee(),dto.getDayOffId().getDate(),dto.getDayOffType());

        return dayOff;
    }
}
