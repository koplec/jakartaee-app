package prv.koplec.jaxrs_todo.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import prv.koplec.jaxrs_todo.entities.Todo;

import java.util.ArrayList;
import java.util.List;

@Path("/todos")
public class TodoResource {

    private static List<Todo> todoList = new ArrayList<>();

    // テストデータを追加
    static {
        Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setTitle("Todo 1");
        todo1.setDescription("Description for Todo 1");
        todo1.setCompleted(false);
        todo1.setDeadline("2022-12-31");
        todoList.add(todo1);

        Todo todo2 = new Todo();
        todo2.setId(2L);
        todo2.setTitle("Todo 2");
        todo2.setDescription("Description for Todo 2");
        todo2.setCompleted(true);
        todo2.setDeadline("2022-12-30");
        todoList.add(todo2);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodos() {
        return Response.ok(todoList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodoById(@PathParam("id") Long id) {
        Todo todo = findTodoById(id);
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
        todo.setId(generateNextId());
        todoList.add(todo);
        return Response.status(Response.Status.CREATED).entity(todo).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodo(@PathParam("id") Long id, Todo updatedTodo) {
        Todo existingTodo = findTodoById(id);
        if (existingTodo != null) {
            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setDescription(updatedTodo.getDescription());
            existingTodo.setCompleted(updatedTodo.isCompleted());
            existingTodo.setDeadline(updatedTodo.getDeadline());
            return Response.ok(existingTodo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodo(@PathParam("id") Long id) {
        Todo todo = findTodoById(id);
        if (todo != null) {
            todoList.remove(todo);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Todo not found").build();
        }
    }

    // ユーティリティメソッド: IDを生成
    private Long generateNextId() {
        return todoList.stream()
                .mapToLong(Todo::getId)
                .max()
                .orElse(0L) + 1;
    }

    // ユーティリティメソッド: IDでTodoを検索
    private Todo findTodoById(Long id) {
        return todoList.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
