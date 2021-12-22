package net.focik.hr.employee.domain.exceptions;

public class EmployeeNotValidException extends ObjectNotValidException {
    public EmployeeNotValidException() {
        super("Employee variable can't be null or empty");
    }
    public EmployeeNotValidException(String message) {
        super(message);
    }
}
