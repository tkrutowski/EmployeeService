package net.focik.hr.employee.domain.exceptions;


import java.time.LocalDate;

public class WorkTimeAlreadyExistException extends RuntimeException {
    public WorkTimeAlreadyExistException(Integer idEmployee, LocalDate date) {
        super("WorkTime with idEmployee = " + idEmployee + " and date = " + date.toString() + " does not exist");
    }
    public WorkTimeAlreadyExistException(String message) {
        super(message);
    }
}
