package se.dajo.taskBackend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.model.data.Task;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.service.IssueService;
import se.dajo.taskBackend.resource.param.TaskParam;
import se.dajo.taskBackend.service.TaskService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.List;

import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static javax.ws.rs.core.Response.Status.CREATED;

@Component
@Path("tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class TaskResource {

    private final TaskService taskService;
    private final IssueService issueService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public TaskResource(TaskService service, IssueService issueService) {
        this.taskService = service;
        this.issueService = issueService;
    }

    @POST
    public Response createTask(Task task) {
        task = taskService.saveTask(task);
        return Response.status(CREATED).header("Location", uriInfo.getAbsolutePathBuilder()
                .path(task.getTaskNumber().toString())).build();
    }

    @POST
    @Path("{taskNumber}/issues")
    public Response createIssue(@PathParam("taskNumber") Long taskNumber, Issue issue) {
        issueService.saveIssue(issue, taskNumber);
        return Response.status(CREATED).header("Location", uriInfo.getAbsolutePathBuilder().path(issue.getDescription())).build();
    }

    @GET
    public Response getTasks(@BeanParam TaskParam taskParam) {
        List<Task> tasks = taskService.getTasks(taskParam);
        return Response.ok(tasks).build();
    }

    @PUT
    @Path("{taskNumber}")
    public Response updateTask(Task task) {
        taskService.saveTask(task);
        return Response.status(ACCEPTED).build();
    }
}
