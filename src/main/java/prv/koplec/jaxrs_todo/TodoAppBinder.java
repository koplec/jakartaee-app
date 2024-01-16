package prv.koplec.jaxrs_todo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import jakarta.inject.Singleton;
import prv.koplec.jaxrs_todo.services.AuthService;
import prv.koplec.jaxrs_todo.services.TodoService;
import prv.koplec.jaxrs_todo.services.TodoServiceImpl;
import prv.koplec.jaxrs_todo.services.UserService;
import prv.koplec.jaxrs_todo.services.UserServiceImpl;

public class TodoAppBinder extends AbstractBinder{
    @Override
    protected void configure() {
        
        bindAsContract(AuthService.class).in(Singleton.class);
        bind(TodoServiceImpl.class).to(TodoService.class)
            .in(Singleton.class);
        bind(UserServiceImpl.class).to(UserService.class)
            .in(Singleton.class);
        //ApplicationScopedなら、in(Singleton.class)を指定する
        //RequestScoped, SessionScopedに対応するものはなさそう
    }
}
