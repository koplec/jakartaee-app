package prv.koplec.jaxrs_todo;

import org.glassfish.jersey.server.ResourceConfig;

public class TodoApplication extends ResourceConfig{
    public TodoApplication() {
        packages("prv.koplec.jaxrs_todo.resource", "prv.koplec.jaxrs_todo.services");
        register(new TodoAppBinder());
        
    }
    
}
