// TodoService.java
package prv.koplec.jaxrs_todo.services;

import java.util.List;

import prv.koplec.jaxrs_todo.entities.Todo;

public interface TodoService {
    List<Todo> getTodos();
    List<Todo> searchTodos(Boolean completed, Long userId, String orderBy, int limit, int offset);
    Todo createTodo(Todo todo);
    Todo updateTodo(Long id, Todo updatedTodo);
    Todo getTodoById(Long id);
    boolean deleteTodo(Long id);
    // 他に必要なメソッドがあれば追加
}
