package net.focik.hr.employee.domain.exceptions;

//@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException() {
        super();
    }
}
