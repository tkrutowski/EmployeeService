package net.focik.hr.employee.domain.addition.port.primary;

import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionType;

public interface AddAdditionUseCase {
    Integer addAddition(Addition addition);

    Integer addAdditionType(AdditionType additionType);

    Integer updateAddition(Addition addition);
}
