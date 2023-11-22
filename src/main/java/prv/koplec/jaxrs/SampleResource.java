package prv.koplec.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sample")
public class SampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "hello";
    }
}
