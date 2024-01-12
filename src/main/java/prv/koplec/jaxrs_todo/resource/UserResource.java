// UserResource.java
package prv.koplec.jaxrs_todo.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prv.koplec.jaxrs_todo.entities.User;
import prv.koplec.jaxrs_todo.services.UserService;
import prv.koplec.jaxrs_todo.services.UserServiceImpl;

import java.util.List;

@Path("/users")
@RolesAllowed("admin")
public class UserResource {

    private final UserService userService;

    public UserResource() {
        this.userService = new UserServiceImpl();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        boolean registered = userService.registerUser(user);
        if (registered) {
            return Response.status(Response.Status.CREATED).entity(user).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("User with the same username already exists").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return Response.ok(userList).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeUser(@PathParam("id") Long id) {
        boolean removed = userService.removeUser(id);
        if (removed) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
    }
}
