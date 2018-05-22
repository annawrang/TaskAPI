package se.dajo.taskBackend.service.exception;

public final class InvalidSpaceInTeamException extends GeneralException {
    public InvalidSpaceInTeamException() {
        super("No space in team for user");
    }
}