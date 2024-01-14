package prv.koplec.jaxrs_todo.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import prv.koplec.jaxrs_todo.entities.Todo;
import prv.koplec.jaxrs_todo.entities.User;
import prv.koplec.jaxrs_todo.services.TodoService;
import prv.koplec.jaxrs_todo.services.TodoServiceImpl;
import prv.koplec.jaxrs_todo.services.UserService;
import prv.koplec.jaxrs_todo.services.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Path("/todos")
public class TodoResource {
    private final TodoService todoService;
    private final UserService userService;

    @Context
    private SecurityContext securityContext;

    // @Inject
    // public TodoResource(TodoService todoService) {
    // this.todoService = todoService;
    // }

    public TodoResource() {
        this.todoService = new TodoServiceImpl();
        this.userService = new UserServiceImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodos(
            @QueryParam("completed") Boolean completed,
            @QueryParam("userId") Long userId,
            @QueryParam("orderBy") String orderBy, 
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("offset") @DefaultValue("0") int offset){
  
        List<Todo> result = todoService.searchTodos(completed, userId, orderBy, limit, offset);
        return Response.ok(result).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodoById(@PathParam("id") Long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            return Response.ok(todo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // todoはほかのユーザIDのものを作ってもよい
    public Response createTodo(Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return Response.status(Response.Status.CREATED).entity(createdTodo).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodo(@PathParam("id") Long id, Todo updatedTodo) {
        // ログインユーザの情報取得
        Principal principal = securityContext.getUserPrincipal();

        if (true || principal != null) {
            String username = principal.getName();
            User principalUser = userService.getUserByUsername(username);
            // ユーザが自分のTodoを更新しようとしているか確認
            Todo existingTodo = todoService.getTodoById(id);
            if (existingTodo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
            }
            Long userId = existingTodo.getUserId();
            if (!principalUser.getId().equals(userId)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("You don't have permission to update this todo").build();
            }

            Todo updated = todoService.updateTodo(id, updatedTodo);
            return Response.ok(updated).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodo(@PathParam("id") Long id) {
        // ログインユーザの情報取得
        Principal principal = securityContext.getUserPrincipal();

        if (principal != null) {
            String username = principal.getName();
            User principalUser = userService.getUserByUsername(username);
            // ユーザが自分のTodoを更新しようとしているか確認
            Todo existingTodo = todoService.getTodoById(id);
            if (existingTodo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
            }
            Long userId = existingTodo.getUserId();
            if (!principalUser.getId().equals(userId)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("You don't have permission to update this todo").build();
            }

            boolean deleted = todoService.deleteTodo(id);
            if (deleted) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build();
        }
    }

    @PUT
    @Path("/{id}/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeTodo(@PathParam("id") Long id) {
        // ログインユーザの情報取得
        Principal principal = securityContext.getUserPrincipal();

        if (true || principal != null) {
            String username = principal.getName();
            User principalUser = userService.getUserByUsername(username);
            // ユーザが自分のTodoを完了しようとしているか確認
            Todo existingTodo = todoService.getTodoById(id);
            if (existingTodo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
            }
            Long userId = existingTodo.getUserId();
            if (!principalUser.getId().equals(userId)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("You don't have permission to complete this todo").build();
            }

            // Todoを完了状態に更新
            existingTodo.setCompleted(true);
            Todo completedTodo = todoService.updateTodo(id, existingTodo);

            return Response.ok(completedTodo).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build();
        }
    }

    @PUT
    @Path("/{id}/uncomplete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response uncompleteTodo(@PathParam("id") Long id) {
        // ログインユーザの情報取得
        Principal principal = securityContext.getUserPrincipal();

        if (true || principal != null) {
            String username = principal.getName();
            User principalUser = userService.getUserByUsername(username);
            // ユーザが自分のTodoを未完了に戻そうとしているか確認
            Todo existingTodo = todoService.getTodoById(id);
            if (existingTodo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
            }
            Long userId = existingTodo.getUserId();
            if (!principalUser.getId().equals(userId)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("You don't have permission to uncomplete this todo").build();
            }

            // Todoを未完了状態に更新
            existingTodo.setCompleted(false);
            Todo uncompletedTodo = todoService.updateTodo(id, existingTodo);

            return Response.ok(uncompletedTodo).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build();
        }
    }

}
