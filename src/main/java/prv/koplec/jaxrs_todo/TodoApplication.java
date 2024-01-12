package prv.koplec.jaxrs_todo;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class TodoApplication extends ResourceConfig{
    public TodoApplication() {
        packages("prv.koplec.jaxrs_todo");

        register(RolesAllowedDynamicFeature.class);
        register(new TodoAppBinder());

    }
    
}
