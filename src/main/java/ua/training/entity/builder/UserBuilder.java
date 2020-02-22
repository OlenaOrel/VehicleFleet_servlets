package ua.training.entity.builder;

import ua.training.entity.Bus;
import ua.training.entity.User;
import ua.training.entity.UserRole;

import java.util.List;

public class UserBuilder {

    private User userBuilder;

    public UserBuilder() {
        userBuilder = new User();
    }

    public UserBuilder id(int id) {
        userBuilder.setId(id);
        return this;
    }

    public UserBuilder firstName(String firstName) {
        userBuilder.setFirstName(firstName);
        return this;
    }

    public UserBuilder lastName(String lastName) {
        userBuilder.setLastName(lastName);
        return this;
    }

    public UserBuilder originFirstName(String originFirstName) {
        userBuilder.setOriginFirstName(originFirstName);
        return this;
    }

    public UserBuilder originLastName(String originLastName) {
        userBuilder.setOriginLastName(originLastName);
        return this;
    }

    public UserBuilder email(String email) {
        userBuilder.setEmail(email);
        return this;
    }

    public UserBuilder password(String password) {
        userBuilder.setPassword(password);
        return this;
    }

    public UserBuilder role(UserRole role) {
        userBuilder.setRole(role);
        return this;
    }

    public UserBuilder busList(List<Bus> buses) {
        userBuilder.setBusList(buses);
        return this;
    }

    public User build() {
        return userBuilder;
    }
}
