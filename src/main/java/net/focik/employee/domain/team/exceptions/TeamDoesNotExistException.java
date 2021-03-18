package net.focik.employee.domain.team.exceptions;

public class TeamDoesNotExistException extends ObjectDoesNotExistException{
    public TeamDoesNotExistException(Integer id) {
        super("Team with id = " + id + " does not exist");
    }
}
