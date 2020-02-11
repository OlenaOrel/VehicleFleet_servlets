package ua.training.model.dao.mapper;

import ua.training.model.entity.User;
import ua.training.model.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    private static final String USER_ID = "users.id";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_ORIGIN_FIRST_NAME = "origin_first_name";
    private static final String USER_ORIGIN_LAST_NAME = "origin_last_name";
    private static final String USER_LOGIN = "login";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ROLE = "role";
    private static final String USER_FREE = "users.free";

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .id(rs.getInt(USER_ID))
                .firstName(rs.getString(USER_FIRST_NAME))
                .lastName(rs.getString(USER_LAST_NAME))
                .originFirstName(rs.getString(USER_ORIGIN_FIRST_NAME))
                .originLastName(rs.getString(USER_ORIGIN_LAST_NAME))
                .login(rs.getString(USER_LOGIN))
                .email(rs.getString(USER_EMAIL))
                .password(rs.getString(USER_PASSWORD))
                .free(rs.getBoolean(USER_FREE))
                .role(UserRole.valueOf(rs.getString(USER_ROLE)))
                .build();
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}