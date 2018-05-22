package se.dajo.taskBackend.service.exception;

public final class InvalidUserNumberException extends GeneralException {
    public InvalidUserNumberException() {
        super("No user found");
    }
}
