package net.focik.hr.employee.api.mapper;

import net.focik.hr.employee.api.dto.AdditionDto;
import net.focik.hr.employee.api.dto.AdvanceDto;
import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionType;
import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.domain.exceptions.AdditionNotValidException;
import net.focik.hr.employee.domain.exceptions.EmployeeNotValidException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ApiAdvanceMapper {

    public Advance toDomain(AdvanceDto dto) {
        valid(dto);
        return Advance.builder()
                .id(dto.getId())
                .idEmployee(dto.getIdEmployee())
                .date(LocalDate.parse(dto.getDate()))
                .amount(BigDecimal.valueOf(Double.parseDouble(dto.getAmount())))
                .otherInfo(dto.getOtherInfo())
                .build();
    }

    public AdvanceDto toDto(Advance a) {
        return AdvanceDto.builder()
                .id(a.getId())
                .idEmployee(a.getIdEmployee())
                .amount(String.format("%.2f", a.getAmount()))
                .date(a.getDate().toString())
                .otherInfo(a.getOtherInfo())
                .build();
    }

    private void valid(AdvanceDto dto) {
        if (dto.getIdEmployee() == 0)
            throw new EmployeeNotValidException("IdEmployee can't be null.");
        if (dto.getDate().isEmpty())
            throw new AdditionNotValidException("Date can't be empty.");
    }
}