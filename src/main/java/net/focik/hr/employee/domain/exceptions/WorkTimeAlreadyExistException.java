package net.focik.hr.employee.domain.exceptions;


import net.focik.hr.utils.exceptions.ObjectAlreadyExistException;

import java.time.LocalDate;

public class WorkTimeAlreadyExistException extends ObjectAlreadyExistException {
    public WorkTimeAlreadyExistException(Integer idEmployee, LocalDate date) {
        super("WorkTime with idEmployee = " + idEmployee + " and date = " + date.toString() + " already exist");
    }
    public WorkTimeAlreadyExistException(String message) {
        super(message);
    }
}
