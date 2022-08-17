package net.focik.hr.employee.domain.exceptions;


import net.focik.hr.utils.exceptions.ObjectNotFoundException;

public class EmployeeNotFoundException extends ObjectNotFoundException {
    public EmployeeNotFoundException(Integer id) {
        super("Employee with id = " + id + " does not exist");
    }

}
