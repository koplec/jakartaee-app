// User.java
package prv.koplec.jaxrs_todo.entities;

public class User {
    private Long id;
    private String name;
    private String password;
    private boolean isAdmin;

    // コンストラクタ、ゲッター、セッターなどを追加

    public User() {
    }

    
    public User(String username, String password) {
        this.name = username;
        this.password = password;
    }

    public User(Long id, String username, String password, boolean isAdmin) {
        this.id = id;
        this.name = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
