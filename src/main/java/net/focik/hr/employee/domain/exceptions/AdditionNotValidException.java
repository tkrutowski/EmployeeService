package net.focik.hr.employee.domain.exceptions;


import net.focik.hr.utils.exceptions.ObjectNotValidException;

public class AdditionNotValidException extends ObjectNotValidException {
    public AdditionNotValidException(String message) {
        super(message);
    }

}
