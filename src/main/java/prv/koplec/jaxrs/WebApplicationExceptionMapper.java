package prv.koplec.jaxrs;

import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {
        String message = exception.getMessage();
        int status = exception.getResponse().getStatus();
        Map<String, String> a = new HashMap<>();
        a.put("message", message);
        a.put("status", Integer.toString(status));
        return Response.status(status).entity(a).type(MediaType.APPLICATION_JSON).build();
    }
}
