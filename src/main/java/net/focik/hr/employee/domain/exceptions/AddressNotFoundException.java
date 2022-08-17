package net.focik.hr.employee.domain.exceptions;

import net.focik.hr.utils.exceptions.ObjectNotFoundException;

public class AddressNotFoundException extends ObjectNotFoundException {
    public AddressNotFoundException(Integer id) {
        super("Address with id = " + id + " does not exist");
    }
}
