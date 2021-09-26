package net.focik.hr.employee.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ObjectDoesNotExistException extends RuntimeException {
    public ObjectDoesNotExistException(String message) {
        super(message);
    }
}
