package ua.training.dao.impl;

import ua.training.dao.UserDao;
import ua.training.dao.mapper.UserMapper;
import ua.training.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBSUserDao implements UserDao {

    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String FIND_USER_BY_BUS_ID_QUERY = "SELECT * FROM user " +
            "LEFT JOIN bus_driver bd " +
            "ON user.id = bd.driver_id " +
            "WHERE bd.bus_id = ?";
    private static final String FIND_NOT_APPOINT_DRIVER_FOR_BUS_QUERY = "SELECT * FROM user " +
            "LEFT JOIN bus_driver bd " +
            "ON user.id = bd.driver_id " +
            "WHERE driver_id NOT IN (SELECT driver_id FROM appointment WHERE date = ?) AND bd.bus_id = ?";

    private Connection connection;
    UserMapper mapper = new UserMapper();

    public JDBSUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_QUERY)) {
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
    public boolean save(User entity) {
        return false;
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_QUERY)) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_BUS_ID_QUERY)) {
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

    public List<User> findNotAppointDriverForBus(int busId) {
        LocalDate date = LocalDate.now();
        List<User> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_NOT_APPOINT_DRIVER_FOR_BUS_QUERY)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setInt(2, busId);
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
}
