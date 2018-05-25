package se.dajo.taskBackend.resource.param;

import javax.ws.rs.QueryParam;

public final class IssueParam {

    @QueryParam("start")
    public Integer start;

    @QueryParam("display")
    public Integer display;
}
