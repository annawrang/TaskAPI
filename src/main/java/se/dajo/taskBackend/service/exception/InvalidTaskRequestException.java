package se.dajo.taskBackend.service.exception;

public final class InvalidTaskRequestException extends GeneralException {
    public InvalidTaskRequestException() {
        super("Could not recognize parameter");
    }
}
