package ua.training.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.UserDao;
import ua.training.dao.mapper.UserMapper;
import ua.training.entity.User;
import ua.training.exception.UserExistException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBSUserDao implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(JDBSUserDao.class);

    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";
    private static final String SAVE_USER_QUERY = "INSERT INTO user " +
            "(first_name, last_name, origin_first_name, origin_last_name, email, password, role)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String FIND_NOT_APPOINT_DRIVER_FOR_BUS_QUERY = "SELECT * FROM user " +
            "LEFT JOIN bus_driver bd " +
            "ON user.id = bd.driver_id " +
            "WHERE driver_id " +
            "NOT IN (SELECT driver_id FROM appointment WHERE date = ?)" +
            " AND bd.bus_id = ?";

    UserMapper mapper = new UserMapper();

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> result = Optional.empty();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void saveUser(User entity) throws UserExistException {
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_QUERY)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getOriginFirstName());
            preparedStatement.setString(4, entity.getOriginLastName());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getPassword());
            preparedStatement.setString(7, entity.getRole().name());
            preparedStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            LOGGER.error("User with email = {} exists", entity.getEmail());
            throw new UserExistException("User with email = " + entity.getEmail() + "exists");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public boolean save(User entity) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> result = Optional.empty();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public List<User> findNotAppointDriverForBus(int busId) {
        LocalDate date = LocalDate.now();
        List<User> result = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_NOT_APPOINT_DRIVER_FOR_BUS_QUERY)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setInt(2, busId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }
}
