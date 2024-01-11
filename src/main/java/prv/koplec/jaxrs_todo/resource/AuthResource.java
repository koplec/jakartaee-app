// AuthResource.java
package prv.koplec.jaxrs_todo.resource;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import prv.koplec.jaxrs_todo.entities.User;
import prv.koplec.jaxrs_todo.services.UserService;
import prv.koplec.jaxrs_todo.services.UserServiceImpl;

import java.util.Date;

import javax.crypto.SecretKey;

@Path("/auth")
public class AuthResource {

    private final UserService userService;

    // @Inject
    // public AuthResource(UserService userService) {
    //     this.userService = userService;
    // }
    public AuthResource(){
        this.userService = new UserServiceImpl();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(User user, @Context UriInfo uriInfo) {
        // ユーザの認証が行われる
        boolean isAuthenticated = authenticateUser(user);

        if (isAuthenticated) {
            // トークンの発行
            String token = issueToken(user.getUsername());
            return token;
        } else {
            return "Authentication failed";
        }
    }

    // ユーザ認証
    private boolean authenticateUser(User user) {
        // ユーザ名とパスワードの検証を UserService で行う
        return userService.authenticateUser(user.getUsername(), user.getPassword());
    }

    // トークンの発行
    private String issueToken(String username) {
        // シークレットキー（安全なキーを生成）
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // トークンの有効期限（ここでは1時間）
        long expirationMillis = System.currentTimeMillis() + 3600000;

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(expirationMillis))
                .signWith(secretKey)
                .compact();
    }
}
