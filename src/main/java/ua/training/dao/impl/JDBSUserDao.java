package ua.training.dao.impl;

import ua.training.dao.UserDao;
import ua.training.dao.mapper.UserMapper;
import ua.training.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBSUserDao implements UserDao {
    private Connection connection;

    public JDBSUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String query = "SELECT * FROM user WHERE email = ?";
        Optional<User> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper mapper = new UserMapper();
            if (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
            close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public Optional<User> findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
