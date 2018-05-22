package se.dajo.taskBackend.service.exception;

import se.dajo.taskBackend.enums.TaskStatus;

public final class InvalidStatusException extends GeneralException {
    public InvalidStatusException() {
        super("Task does not have Status DONE, cannot add Issue");
    }

    public InvalidStatusException(TaskStatus status) {
        super("No task with status " + status.toString() + " found");
    }
}
