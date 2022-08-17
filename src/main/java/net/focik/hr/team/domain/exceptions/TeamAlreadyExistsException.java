package net.focik.hr.team.domain.exceptions;

import net.focik.hr.utils.exceptions.ObjectAlreadyExistException;

public class TeamAlreadyExistsException extends ObjectAlreadyExistException {
    public TeamAlreadyExistsException(Integer id) {
        super("Team with id: " + id + " already exists.");
    }
}
