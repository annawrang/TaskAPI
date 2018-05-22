package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.Status;

public final class Team {

    private String teamName;
    private Status status;

    public Team(String teamName, Status status) {
        this.teamName = teamName;
        this.status = status;
    }

    public String getTeamName() {
        return teamName;
    }

    public Status getStatus() {
        return status;
    }
}
