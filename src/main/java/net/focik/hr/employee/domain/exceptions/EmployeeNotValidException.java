package net.focik.hr.employee.domain.exceptions;

public class EmployeeNotValidException extends ObjectDoesNotExistException {
    public EmployeeNotValidException() {
        super("Employee variable can't be null or empty");
    }

}
