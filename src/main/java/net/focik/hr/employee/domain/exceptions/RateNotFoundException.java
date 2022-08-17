package net.focik.hr.employee.domain.exceptions;

import net.focik.hr.utils.exceptions.ObjectNotFoundException;

public class RateNotFoundException extends ObjectNotFoundException {
    public RateNotFoundException(Integer id) {
        super("Rate with id = " + id + " does not exist");
    }
}
