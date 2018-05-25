package se.dajo.taskBackend.service.exception;

public class InvalidPagingRequestException extends GeneralException {
    public InvalidPagingRequestException(String s) { super("Page doesn't exist");
    }
}
