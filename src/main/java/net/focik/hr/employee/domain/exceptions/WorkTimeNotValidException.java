package net.focik.hr.employee.domain.exceptions;

public class WorkTimeNotValidException extends ObjectDoesNotExistException {
    public WorkTimeNotValidException(String message) {
        super(message);
    }

}
