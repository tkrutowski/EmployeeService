package net.focik.hr.employee.domain.addition.port.secondary;

import net.focik.hr.employee.domain.addition.AdditionType;

import java.util.List;

public interface AdditionTypeRepository {

    List<AdditionType> findAll();

    Integer add(AdditionType additionType);
}
