package net.focik.hr.employee.domain.exceptions;

public class AdvanceNotValidException extends ObjectNotValidException {
    public AdvanceNotValidException() {
        super("Advance variable can't be null or empty");
    }

}
