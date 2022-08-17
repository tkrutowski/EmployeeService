package net.focik.hr.employee.domain.exceptions;

import net.focik.hr.utils.exceptions.ObjectNotFoundException;

public class DaysToWorkNotFoundException extends ObjectNotFoundException {
    public DaysToWorkNotFoundException(Integer year, Integer month) {
        super("DaysToWork by date " + year + "." + month + " does not exist");
    }
}
