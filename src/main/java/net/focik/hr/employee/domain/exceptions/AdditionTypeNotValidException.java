package net.focik.hr.employee.domain.exceptions;


import net.focik.hr.utils.exceptions.ObjectNotValidException;

public class AdditionTypeNotValidException extends ObjectNotValidException {
    public AdditionTypeNotValidException(String message) {
        super(message);
    }

}
