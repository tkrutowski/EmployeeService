package net.focik.hr.employee.domain.addition.port.primary;

import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionType;

import java.time.LocalDate;
import java.util.List;

public interface GetAdditionUseCase {
    Addition getAddition(Integer id);

    List<Addition> getAdditions(Integer idEmployee, LocalDate date);

    List<AdditionType> getAdditionTypes();
}
