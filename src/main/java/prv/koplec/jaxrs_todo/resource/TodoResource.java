package prv.koplec.jaxrs_todo.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prv.koplec.jaxrs_todo.entities.Todo;
import prv.koplec.jaxrs_todo.services.TodoService;
import prv.koplec.jaxrs_todo.services.TodoServiceImpl;

import java.util.List;

@Path("/todos")
public class TodoResource {
    private final TodoService todoService;

    // @Inject
    // public TodoResource(TodoService todoService) {
    //     this.todoService = todoService;
    // }

    public TodoResource() {
        this.todoService = new TodoServiceImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodos() {
        List<Todo> todoList = todoService.getTodos();
        return Response.ok(todoList).build();
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
    public Response createTodo(Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return Response.status(Response.Status.CREATED).entity(createdTodo).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodo(@PathParam("id") Long id, Todo updatedTodo) {
        Todo existingTodo = todoService.updateTodo(id, updatedTodo);
        if (existingTodo != null) {
            return Response.ok(existingTodo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodo(@PathParam("id") Long id) {
        boolean deleted  = todoService.deleteTodo(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
        }
    }


}
