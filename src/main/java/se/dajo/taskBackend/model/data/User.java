package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.Status;

public final class User {

    private String firstName;
    private String surName;
    private Long userNumber;
    private Status status;

    public User(String firstName, String surName, Long userNumber, Status status) {
        this.firstName = firstName;
        this.surName = surName;
        this.userNumber = userNumber;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public Status getStatus() {
        return status;
    }

    public User setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
        return this;
    }

    public User setStatus(Status status) {
        this.status = status;
        return this;
    }
}
