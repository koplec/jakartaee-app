// UserServiceImpl.java
package prv.koplec.jaxrs_todo.services;

import jakarta.inject.Singleton;
import prv.koplec.jaxrs_todo.entities.User;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    private static List<User> userList = new ArrayList<>();

    // テストデータを追加
    static {
        User user1 = new User(1L, "user1", "password1");
        User user2 = new User(2L, "user2", "password2");

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
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = getUserByUsername(username);

        // ユーザが存在し、かつパスワードが一致すれば認証成功
        return user != null && user.getPassword().equals(password);
    }
}
