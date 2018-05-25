package se.dajo.taskBackend.model.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import se.dajo.taskBackend.enums.TaskStatus;

import java.time.LocalDate;

public final class Task {

    private String description;
    private TaskStatus status;
    private Long taskNumber;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createdDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startedDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate doneDate;

    // den som används från JSON!
    public Task(String description, TaskStatus status, Long taskNumber) {
        this.description = description;
        this.status = status;
        this.taskNumber = taskNumber;
        this.createdDate = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Long getTaskNumber() {
        return taskNumber;
    }

    public Task setTaskNumber(Long taskNumber) {
        this.taskNumber = taskNumber;
        return this;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getStartedDate() {
        return startedDate;
    }

    public LocalDate getDoneDate() {
        return doneDate;
    }

    public Task setStartedDate(LocalDate startedDate) {
        this.startedDate = startedDate;
        return this;
    }

    public Task setDoneDate(LocalDate doneDate) {
        this.doneDate = doneDate;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Task && ((Task) object).taskNumber == taskNumber;
    }

    @Override
    public int hashCode() {
        String last4digist = taskNumber.toString();
        last4digist = last4digist.substring(6);
        return Integer.parseInt(last4digist);
    }

    public Task setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}
