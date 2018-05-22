package se.dajo.taskBackend.service.exception;

public final class OverworkedUserException extends GeneralException {
    public OverworkedUserException() {
        super("Poor user has too little time");
    }
}
