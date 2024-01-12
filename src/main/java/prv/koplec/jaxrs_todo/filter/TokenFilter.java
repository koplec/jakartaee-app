// TokenFilter.java
package prv.koplec.jaxrs_todo.filter;

import io.jsonwebtoken.ExpiredJwtException;
import prv.koplec.jaxrs_todo.entities.User;
import prv.koplec.jaxrs_todo.services.AuthService;
import prv.koplec.jaxrs_todo.services.UserService;
import prv.koplec.jaxrs_todo.services.UserServiceImpl;

import jakarta.annotation.Priority;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(1)
public class TokenFilter implements ContainerRequestFilter {

    @Context
    private UriInfo uriInfo;
    
    private final UserService userService;
    private final AuthService authService;

    public TokenFilter() {
        this.userService = new UserServiceImpl();
        this.authService = new AuthService();
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // リクエストパスの取得
        String path = uriInfo.getPath();
        if(path.startsWith("auth")){
            return;
        }
        // Authorization ヘッダーからトークンを取得
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                // トークンを検証
                String token = authorizationHeader.substring("Bearer".length()).trim();
                validateToken(token, requestContext);
            } catch (ExpiredJwtException e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token has expired").build());
            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid token").build());
            }
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token is missing").build());
        }
    }

    private void validateToken(String token, ContainerRequestContext requestContext) {
        // トークンからユーザ名を取得
        String username = authService.getSubject(token);

        // ユーザ名が存在し、かつユーザが有効ならば、リクエストを許可
        if(username == null){
            throw new RuntimeException("User is not valid");
        }
        User user = userService.getUserByUsername(username);
        if (user != null) {
            // リクエストを通す
            requestContext.setProperty("user", user);
        } else {
            // ユーザが無効な場合、リクエストを拒否
            throw new RuntimeException("User is not valid");
        }
    }
}
