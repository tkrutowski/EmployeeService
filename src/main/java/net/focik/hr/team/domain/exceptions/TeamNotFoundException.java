package net.focik.hr.team.domain.exceptions;

public class TeamNotFoundException extends ObjectNotFoundException {
    public TeamNotFoundException(Integer id) {
        super("Team with id = " + id + " does not exist");
    }
}
