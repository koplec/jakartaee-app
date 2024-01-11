
package prv.koplec.jaxrs_todo.services;

import prv.koplec.jaxrs_todo.entities.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByUsername(String username);
    boolean authenticateUser(String username, String password);
}