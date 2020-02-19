package ua.training.dao.mapper;

import ua.training.entity.User;
import ua.training.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {

    private static final String USER_ID = "user.id";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_ORIGIN_FIRST_NAME = "origin_first_name";
    private static final String USER_ORIGIN_LAST_NAME = "origin_last_name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ROLE = "role";

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .id(rs.getInt(USER_ID))
                .firstName(rs.getString(USER_FIRST_NAME))
                .lastName(rs.getString(USER_LAST_NAME))
                .originFirstName(rs.getString(USER_ORIGIN_FIRST_NAME))
                .originLastName(rs.getString(USER_ORIGIN_LAST_NAME))
                .email(rs.getString(USER_EMAIL))
                .password(rs.getString(USER_PASSWORD))
                .role(UserRole.valueOf(rs.getString(USER_ROLE)))
                .build();
    }

}
