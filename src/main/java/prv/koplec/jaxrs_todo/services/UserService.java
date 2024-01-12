// UserService.java
package prv.koplec.jaxrs_todo.services;

import prv.koplec.jaxrs_todo.entities.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User getUserByUsername(String username);

    boolean authenticateUser(String username, String password);

    boolean registerUser(User user);

    boolean removeUser(Long id);

    List<User> getAllUsers();
}
