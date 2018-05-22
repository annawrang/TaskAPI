package se.dajo.taskBackend.config;

import se.dajo.taskBackend.resource.TaskResource;
import se.dajo.taskBackend.resource.TeamResource;
import se.dajo.taskBackend.resource.UserResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import se.dajo.taskBackend.resource.mapper.*;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(UserResource.class);
        register(TeamResource.class);
        register(TaskResource.class);
        register(GeneralExceptionMapper.class);
    }
}

