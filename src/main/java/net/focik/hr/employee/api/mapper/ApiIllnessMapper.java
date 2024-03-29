package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.IllnessDto;
import net.focik.hr.employee.api.dto.IllnessTypeDto;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import net.focik.hr.employee.domain.exceptions.WorkTimeNotValidException;
import net.focik.hr.employee.domain.worktimerecords.Illness;
import net.focik.hr.employee.domain.worktimerecords.share.IllnessType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApiIllnessMapper {

    public IllnessTypeDto toDto(IllnessType type) {

        return new IllnessTypeDto(type.getIdValue(), type.getDbValue());
    }

    public Illness toDomain(IllnessDto dto) {
        valid(dto);
        return new Illness(dto.getIdEmployee(),
                LocalDate.parse(dto.getDate()),
                IllnessType.fromIdValue(dto.getIdIllnessType()));
    }

    private void valid(IllnessDto dto) {
        if (dto.getIdEmployee() == null)
            throw new EmployeeNotValidException("IdEmployee can't be null.");

        if (dto.getDate().isEmpty())
            throw new WorkTimeNotValidException("Date can't be empty.");
        if (dto.getIdIllnessType() > 0)
            throw new WorkTimeNotValidException("Incorect id number. Must be > 0.");
    }
}