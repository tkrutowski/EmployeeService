package net.focik.hr.employee.domain.exceptions;

import net.focik.hr.utils.exceptions.ObjectNotFoundException;

public class LoanNotFoundException extends ObjectNotFoundException {
    public LoanNotFoundException(Integer id) {
        super("Loan with id = " + id + " does not exist");
    }
}
