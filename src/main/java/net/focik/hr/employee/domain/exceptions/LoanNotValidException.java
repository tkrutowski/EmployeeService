package net.focik.hr.employee.domain.exceptions;

import net.focik.hr.utils.exceptions.ObjectNotValidException;

public class LoanNotValidException extends ObjectNotValidException {
    public LoanNotValidException(String message) {
        super(message);
    }

    public LoanNotValidException() {
        super("Loan variable can't be null or empty");
    }

}
