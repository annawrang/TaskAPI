package se.dajo.taskBackend.resource.filter;


import se.dajo.taskBackend.resource.Auth;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.Priorities.AUTHENTICATION;

@Auth
@Provider
@Priority(AUTHENTICATION)
public final class AuthorizeFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        String authToken = context.getHeaderString("Auth-Token");
        if(!"key".equals(authToken)){
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(singletonMap("Eror", "Missing/invalid auth-token"))
                    .build());
        }
    }
}
