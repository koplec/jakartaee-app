package prv.koplec.jaxrs_todo.dto.response;

import prv.koplec.jaxrs_todo.entities.User;

public class UserResponseDto {
    private Long id;
    private String name;
    private boolean isAdmin;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.isAdmin = user.isAdmin();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}