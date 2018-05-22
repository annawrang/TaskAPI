package se.dajo.taskBackend.service.exception;

public final class InvalidTaskNumberException extends GeneralException {
    public InvalidTaskNumberException() {
        super("No task found");
    }
}
