package prv.koplec.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sample")
public class SampleResource {

    // localhost:8080/webapp/api/sampleで表示されることを確認
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "hello";
    }
}
