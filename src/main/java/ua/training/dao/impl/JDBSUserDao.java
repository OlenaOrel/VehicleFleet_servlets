package ua.training.dao.impl;

import ua.training.dao.UserDao;
import ua.training.dao.mapper.UserMapper;
import ua.training.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBSUserDao implements UserDao {
    private Connection connection;
    UserMapper mapper = new UserMapper();

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
    public void save(User entity) {

    }

    @Override
    public Optional<User> findById(int id) {
        final String query = "SELECT * FROM user WHERE id = ?";
        Optional<User> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    public List<User> findByBuses_id(int busId) {
        List<User> result = new ArrayList<>();
        String query = "SELECT * FROM user left" +
                " join bus_driver bd" +
                " on user.id = bd.driver_id " +
                " where bd.bus_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, busId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
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
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
