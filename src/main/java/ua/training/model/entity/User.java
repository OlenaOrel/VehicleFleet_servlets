package ua.training.model.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String originFirstName;
    private String originLastName;
    private String login;
    private String email;
    private String password;
    private UserRole role;
    private boolean free;

    private List<Bus> buses;
    private List<Route> routes;

    public User() {
    }

    public User(
            int id,
            String firstName,
            String lastName,
            String originFirstName,
            String originLastName,
            String login,
            String email,
            String password,
            UserRole role,
            List<Bus> buses,
            List<Route> routes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.originFirstName = originFirstName;
        this.originLastName = originLastName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.buses = buses;
        this.routes = routes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOriginFirstName() {
        return originFirstName;
    }

    public void setOriginFirstName(String originFirstName) {
        this.originFirstName = originFirstName;
    }

    public String getOriginLastName() {
        return originLastName;
    }

    public void setOriginLastName(String originLastName) {
        this.originLastName = originLastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public List<Bus> getBusList() {
        return buses;
    }

    public void setBusList(List<Bus> buses) {
        this.buses = buses;
    }

    public List<Route> getRouteList() {
        return routes;
    }

    public void setRouteList(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                free == user.free &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(originFirstName, user.originFirstName) &&
                Objects.equals(originLastName, user.originLastName) &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName,
                originFirstName, originLastName,
                login, email, password, role, free);
    }

    public static class Builder {
        private User userBuilder;

        public Builder() {
            userBuilder = new User();
        }

        public Builder id(int id) {
            userBuilder.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            userBuilder.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            userBuilder.lastName = lastName;
            return this;
        }

        public Builder originFirstName(String originFirstName) {
            userBuilder.originFirstName = originFirstName;
            return this;
        }

        public Builder originLastName(String originLastName) {
            userBuilder.originLastName = originLastName;
            return this;
        }

        public Builder login(String login) {
            userBuilder.login = login;
            return this;
        }

        public Builder email(String email) {
            userBuilder.email = email;
            return this;
        }

        public Builder password(String password) {
            userBuilder.password = password;
            return this;
        }

        public Builder free(boolean free) {
            userBuilder.free = free;
            return this;
        }


        public Builder role(UserRole role) {
            userBuilder.role = role;
            return this;
        }

        public Builder busList(List<Bus> buses) {
            userBuilder.buses = buses;
            return this;
        }

        public Builder bus(Bus bus) {
            userBuilder.buses.add(bus);
            return this;
        }

        public Builder routeList(List<Route> routes) {
            userBuilder.routes = routes;
            return this;
        }

        public Builder route(Route route) {
            userBuilder.routes.add(route);
            return this;
        }

        public User build() {
            return userBuilder;
        }
    }
}