package se.dajo.taskBackend.repository.data;

import se.dajo.taskBackend.enums.TaskStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public final class TaskDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private TaskStatus status;
    private Long taskNumber;
    private LocalDate createdDate;
    private LocalDate startedDate;
    private LocalDate doneDate;


    @ManyToOne
    private UserDTO user;

    protected TaskDTO() {
    }

    // eftersom jag har setters nu kan de här konstruktorerna slås ihop till EN eller TVÅ
    public TaskDTO(Long id, String description, TaskStatus status, Long taskNumber, UserDTO user, LocalDate createdDate) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.taskNumber = taskNumber;
        this.user = user;
        this.createdDate = createdDate;
    }


    public TaskDTO(String description, TaskStatus status, Long taskNumber, LocalDate createdDate) {
        this.description = description;
        this.status = status;
        this.taskNumber = taskNumber;
        this.createdDate = createdDate;
    }

    public TaskDTO(Long id, String description, TaskStatus status, Long taskNumber, LocalDate createdDate) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.taskNumber = taskNumber;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public UserDTO getUser() {
        return user;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Long getTaskNumber() {
        return taskNumber;
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

    public void setStatus(TaskStatus status) {
        this.status = status;
        switch (status) {
            case STARTED:
                this.startedDate = LocalDate.now();
                break;
            case DONE:
                this.doneDate = LocalDate.now();
                break;
        }
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setStartedDate(LocalDate startedDate) {
        this.startedDate = startedDate;
    }
}

