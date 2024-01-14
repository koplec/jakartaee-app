package prv.koplec.jaxrs_todo.entities;

public class Todo {
    private Long id;

    private String title;
    private String description;
    private boolean completed;
    private String dueDate;
    private Long userId;
    private String userName;

    public Todo() {
    }

    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String deadline) {
        this.dueDate = deadline;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
