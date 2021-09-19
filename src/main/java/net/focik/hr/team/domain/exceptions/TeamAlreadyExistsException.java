package net.focik.hr.team.domain.exceptions;

public class TeamAlreadyExistsException extends ObjectAlreadyExistException {
    public TeamAlreadyExistsException(Integer id) {
        super("Team with id: " + id + " already exists.");
    }
}
