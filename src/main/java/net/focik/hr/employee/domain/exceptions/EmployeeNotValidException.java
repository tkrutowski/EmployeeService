package net.focik.hr.employee.domain.exceptions;

import net.focik.hr.utils.exceptions.ObjectNotValidException;

public class EmployeeNotValidException extends ObjectNotValidException {
    public EmployeeNotValidException() {
        super("Employee variable can't be null or empty");
    }
    public EmployeeNotValidException(String message) {
        super(message);
    }
}
