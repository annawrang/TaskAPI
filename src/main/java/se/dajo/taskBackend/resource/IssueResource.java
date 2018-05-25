package se.dajo.taskBackend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.resource.param.IssueParam;
import se.dajo.taskBackend.service.IssueService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("issues")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IssueResource {

    private final IssueService service;

    @Autowired
    public IssueResource(IssueService service){
        this.service = service;
    }

    @GET
    public Response getAllIssues(@BeanParam IssueParam issueParam){
        List<Issue> issues = service.getAllIssues(issueParam);
        return Response.ok(issues).build();
    }
}
