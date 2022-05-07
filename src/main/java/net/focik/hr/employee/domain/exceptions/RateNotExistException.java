package net.focik.hr.employee.domain.exceptions;

public class RateNotExistException extends ObjectNotFoundException {
    public RateNotExistException(Integer id) {
        super("Rate with id = " + id + " does not exist");
    }
}
