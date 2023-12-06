package prv.koplec.jaxrs;

import java.io.IOException;
import java.io.InputStream;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;

@Provider
public class MyRequestFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        UriInfo uriInfo = requestContext.getUriInfo(); //リクエストURL情報
        //リクエストヘッダ情報
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        var cookies = requestContext.getCookies();

        //リクエストボディ
        if(requestContext.hasEntity()){
            InputStream bodyStream = requestContext.getEntityStream();
        
        }
        requestContext.setProperty("foo", "fooValue");
    }
    
}
