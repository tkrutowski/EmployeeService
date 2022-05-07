package net.focik.hr.employee.domain.exceptions;

public class GasMainDoesNotExistException extends ObjectNotFoundException {
    public GasMainDoesNotExistException(Integer id) {
        super("GasMain with id = " + id + " does not exist");
    }
}
