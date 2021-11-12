package net.focik.hr.employee.domain.exceptions;

public class DaysToWorkNotExistException extends ObjectDoesNotExistException {
    public DaysToWorkNotExistException(Integer year, Integer month) {
        super("DaysToWork by date " + year + "." + month + " does not exist");
    }
}
