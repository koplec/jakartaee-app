// UserServiceImpl.java
package prv.koplec.jaxrs_todo.services;

import jakarta.inject.Singleton;
import prv.koplec.jaxrs_todo.entities.User;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    private static List<User> userList = new ArrayList<>();

    static {
        User user1 = new User(1L, "user1", "password1", true);
        User user2 = new User(2L, "user2", "password2", false);

        userList.add(user1);
        userList.add(user2);
    }

    @Override
    public User getUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userList.stream()
                .filter(user -> user.getName().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public boolean registerUser(User user) {
        if (getUserByUsername(user.getName()) == null) {
            user.setId(generateNextId());
            userList.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            userList.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    private Long generateNextId() {
        return userList.stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0L) + 1;
    }
}
