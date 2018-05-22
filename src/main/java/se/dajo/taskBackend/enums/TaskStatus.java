package se.dajo.taskBackend.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaskStatus {
    @JsonProperty("unstarted")
    UNSTARTED,
    @JsonProperty("started")
    STARTED,
    @JsonProperty("done")
    DONE,
    @JsonProperty("annulled")
    ANNULLED
}
