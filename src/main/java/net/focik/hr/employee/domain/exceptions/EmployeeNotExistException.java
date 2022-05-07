package net.focik.hr.employee.domain.exceptions;


public class EmployeeNotExistException extends ObjectNotFoundException {
    public EmployeeNotExistException(Integer id) {
        super("Employee with id = " + id + " does not exist");
    }

}
