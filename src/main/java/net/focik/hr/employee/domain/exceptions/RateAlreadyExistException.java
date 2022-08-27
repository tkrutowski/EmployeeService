package net.focik.hr.employee.domain.exceptions;


import net.focik.hr.utils.exceptions.ObjectAlreadyExistException;

import java.time.LocalDate;

public class RateAlreadyExistException extends ObjectAlreadyExistException {
    public RateAlreadyExistException(Integer idEmployee, LocalDate date) {
        super("Rate with idEmployee = " + idEmployee + " and date = " + date.toString() + " already exist");
    }
    public RateAlreadyExistException(String message) {
        super(message);
    }
}
