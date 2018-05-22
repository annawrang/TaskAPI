package se.dajo.taskBackend.service.exception;

public final class InvalidTeamNameException extends GeneralException{
    public InvalidTeamNameException() {
        super("No team found");
    }
}
