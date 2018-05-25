package se.dajo.taskBackend.service.exception;

public class InvalidDateInputException extends GeneralException {
    public InvalidDateInputException(){ super("The date must be formatted like yyyy-mm-dd");}
}
