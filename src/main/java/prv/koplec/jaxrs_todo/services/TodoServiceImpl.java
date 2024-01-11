// TodoServiceImpl.java
package prv.koplec.jaxrs_todo.services;

import jakarta.inject.Singleton;
import prv.koplec.jaxrs_todo.entities.Todo;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class TodoServiceImpl implements TodoService {

    private static List<Todo> todoList = new ArrayList<>();

    // 初期データの追加
    static {
        Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setTitle("Sample Todo 1");
        todo1.setDescription("This is a sample todo.");
        todo1.setCompleted(false);
        todo1.setDeadline("2022-12-31");
        todoList.add(todo1);

        Todo todo2 = new Todo();
        todo2.setId(2L);
        todo2.setTitle("Sample Todo 2");
        todo2.setDescription("Another sample todo.");
        todo2.setCompleted(true);
        todo2.setDeadline("2022-12-30");
        todoList.add(todo2);

        Todo todo3 = new Todo();
        todo3.setId(3L);
        todo3.setTitle("Sample Todo 3");
        todo3.setDescription("Yet another sample todo.");
        todo3.setCompleted(false);
        todo3.setDeadline("2022-12-29");
        todoList.add(todo3);
    }

    @Override
    public Todo createTodo(Todo todo) {
        todo.setId(generateNextId());
        todoList.add(todo);
        return todo;
    }

    @Override
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo existingTodo = findTodoById(id);
        if (existingTodo != null) {
            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setDescription(updatedTodo.getDescription());
            existingTodo.setCompleted(updatedTodo.isCompleted());
            existingTodo.setDeadline(updatedTodo.getDeadline());
        }
        return existingTodo;
    }

    

    @Override
    public List<Todo> getTodos() {
        return todoList;
    }

    @Override
    public Todo getTodoById(Long id) {
        return findTodoById(id);
    }

    @Override
    public boolean deleteTodo(Long id) {
        Todo todo = findTodoById(id);
        if (todo != null) {
            todoList.remove(todo);
            return true;
        }
        return false;
    }

    private Long generateNextId() {
        return todoList.stream()
                .mapToLong(Todo::getId)
                .max()
                .orElse(0L) + 1;
    }

    private Todo findTodoById(Long id) {
        return todoList.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}