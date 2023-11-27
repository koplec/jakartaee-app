package prv.koplec.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;

public class MyResourceConfig extends ResourceConfig{
    public MyResourceConfig(){
        packages("prv.koplec.jaxrs");
        register(WebApplicationExceptionMapper.class);
    }
    
}
