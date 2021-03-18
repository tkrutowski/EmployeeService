package net.focik.employee.domain.team.exceptions;

public class TeamAlreadyExistsException extends ObjectAlreadyExistException {
    public TeamAlreadyExistsException(Integer id) {
        super("Team with id: " + id + " already exists.");
    }
}
