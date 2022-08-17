package net.focik.hr.employee.domain.exceptions;


import net.focik.hr.utils.exceptions.ObjectNotValidException;

public class WorkTimeNotValidException extends ObjectNotValidException {
    public WorkTimeNotValidException(String message) {
        super(message);
    }

}
