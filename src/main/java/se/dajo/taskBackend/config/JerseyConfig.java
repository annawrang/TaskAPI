package se.dajo.taskBackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import se.dajo.taskBackend.resource.Auth;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("se.dajo.taskBackend.resource");
    }

    @Bean
    public ObjectMapper objectMapper() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return new ObjectMapper().registerModule(new ParameterNamesModule()).setDateFormat(df);
    }
}

