package ua.training.model.dao.mapper;

import ua.training.model.entity.User;
import ua.training.model.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ua.training.constants.DBColumnLabels.*;

public class UserMapper implements ObjectMapper<User> {
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
