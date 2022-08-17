package net.focik.hr.employee.domain.exceptions;

import net.focik.hr.utils.exceptions.ObjectNotValidException;

public class AdvanceNotValidException extends ObjectNotValidException {
    public AdvanceNotValidException() {
        super("Advance variable can't be null or empty");
    }

}
