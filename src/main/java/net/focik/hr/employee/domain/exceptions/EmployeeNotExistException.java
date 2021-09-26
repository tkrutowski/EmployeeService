package net.focik.hr.employee.domain.exceptions;

import java.util.function.Supplier;

public class EmployeeNotExistException extends ObjectDoesNotExistException {
    public EmployeeNotExistException(Integer id) {
        super("Employee with id = " + id + " does not exist");
    }

}
