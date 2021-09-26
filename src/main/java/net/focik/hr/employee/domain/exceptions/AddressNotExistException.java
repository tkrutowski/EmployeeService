package net.focik.hr.employee.domain.exceptions;

public class AddressNotExistException extends ObjectDoesNotExistException {
    public AddressNotExistException(Integer id) {
        super("Address with id = " + id + " does not exist");
    }
}
