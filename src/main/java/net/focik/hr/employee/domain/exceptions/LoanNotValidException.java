package net.focik.hr.employee.domain.exceptions;

public class LoanNotValidException extends ObjectNotValidException {
    public LoanNotValidException() {
        super("Loan variable can't be null or empty");
    }

}
