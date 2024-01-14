package prv.koplec.jaxrs_todo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import prv.koplec.jaxrs_todo.services.TodoService;
import prv.koplec.jaxrs_todo.services.TodoServiceImpl;

public class TodoAppBinder extends AbstractBinder{
    @Override
    protected void configure() {
        bind(TodoServiceImpl.class).to(TodoService.class);
    }
}
