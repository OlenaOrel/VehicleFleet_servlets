package ua.training.model.entity.builder;

import ua.training.model.entity.Bus;
import ua.training.model.entity.User;
import ua.training.model.entity.UserRole;

import java.util.List;

public class UserBuilder {

    private User userBuilder;

    private int id;
    private String firstName;
    private String lastName;
    private String originFirstName;
    private String originLastName;
    private String email;
    private String password;
    private UserRole role;
    private List<Bus> buses;

    public UserBuilder() {
        userBuilder = new User();
    }

    public void firstName(String firstName) {
        this.firstName = firstName;
    }

    public void lastName(String lastName) {
        this.lastName = lastName;
    }

    public void originFirstName(String originFirstName) {
        this.originFirstName = originFirstName;
    }

    public void originLastName(String originLastName) {
        this.originLastName = originLastName;
    }

    public void email(String email) {
        this.email = email;
    }

    public void password(String password) {
        this.password = password;
    }

    public void role(UserRole role) {
        this.role = role;
    }

    public void busList(List<Bus> buses) {
        this.buses = buses;
    }

    public void bus(Bus bus) {
        this.buses.add(bus);
    }

    public User build() {
        userBuilder.setId(id);
        userBuilder.setFirstName(firstName);
        userBuilder.setLastName(lastName);
        userBuilder.setOriginFirstName(originFirstName);
        userBuilder.setOriginLastName(originLastName);
        userBuilder.setEmail(email);
        userBuilder.setPassword(password);
        userBuilder.setRole(role);
        return userBuilder;
    }
}
