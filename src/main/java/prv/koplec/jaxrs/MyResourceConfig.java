package prv.koplec.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;

public class MyResourceConfig extends ResourceConfig{
    public MyResourceConfig(){
        packages("prv.koplec.jaxrs");

        //@Providerがないときは、下記でExceptionMapperを登録する？
        register(WebApplicationExceptionMapper.class);
    }
    
}
