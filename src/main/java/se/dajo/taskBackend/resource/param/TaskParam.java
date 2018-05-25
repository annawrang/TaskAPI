package se.dajo.taskBackend.resource.param;

import javax.ws.rs.QueryParam;

public final class TaskParam {

    @QueryParam("text")
    public String text;

    @QueryParam("status")
    public String status;

    @QueryParam("issue")
    public Boolean issue;

    @QueryParam("start")
    public Integer start;

    @QueryParam("display")
    public Integer display;

    @QueryParam("fromDate")
    public String fromDate;

    @QueryParam("toDate")
    public String toDate;

}
