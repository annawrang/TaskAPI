package se.dajo.taskBackend.resource.param;

import javax.ws.rs.QueryParam;

public final class UserParam {

    @QueryParam("firstName")
    public String firstName;

    @QueryParam("surName")
    public String surName;

    @QueryParam("userNumber")
    public Long userNumber;
}
