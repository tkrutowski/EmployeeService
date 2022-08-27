package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.AdditionDto;
import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionType;
import net.focik.hr.employee.domain.exceptions.AdditionNotValidException;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ApiAdditionMapper {

    public Addition toDomain(AdditionDto dto) {
        valid(dto);
        return Addition.builder()
                .id(dto.getId())
                .idEmployee(dto.getIdEmployee())
                .date(LocalDate.parse(dto.getDate()))
                .amount(BigDecimal.valueOf(Double.parseDouble(dto.getAmount())))
                .otherInfo(dto.getOtherInfo())
                .additionType(new AdditionType(Integer.parseInt(dto.getAdditionType()), null))
                .build();
    }

    public AdditionDto toDto(Addition a) {
        return AdditionDto.builder()
                .id(a.getId())
                .idEmployee(a.getIdEmployee())
                .amount(a.getAmount().stripTrailingZeros().toString())
                .date(a.getDate().toString())
                .otherInfo(a.getOtherInfo())
                .additionType(a.getAdditionTypeName())
                .additionTypeId(a.getAdditionTypeId())
                .build();
    }

    private void valid(AdditionDto dto) {
        if (dto.getIdEmployee() == 0)
            throw new EmployeeNotValidException("IdEmployee can't be null.");
        if (dto.getDate().isEmpty())
            throw new AdditionNotValidException("Date can't be empty.");
        if (Integer.parseInt(dto.getAdditionType()) <= 0)
            throw new AdditionNotValidException("Incorrect id number. Must be > 0.");
    }
}
