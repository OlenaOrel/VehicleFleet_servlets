package ua.training.dto;

import ua.training.entity.UserRole;

public class UserDto {

    private int id;
    private String email;
    private UserRole role;

    public UserDto() {
    }

    public UserDto(int id, String email, UserRole role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email +
                ", role=" + role +
                '}';
    }
}
