package ua.training.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.RouteDao;
import ua.training.dao.mapper.RouteMapper;
import ua.training.entity.Route;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCRouteDao implements RouteDao {

    private static final Logger LOGGER = LogManager.getLogger(JDBCRouteDao.class);

    private static final String FIND_ROUTE_BY_ID_QUERY = "SELECT * FROM route WHERE id = ?";
    private static final String FIND_ALL_ROUTES_QUERY = "SELECT * FROM route";
    private static final String FIND_NOT_APPOINT_ROUTE_QUERY = "SELECT * FROM route WHERE id " +
            "NOT IN (SELECT route_id FROM appointment WHERE date = ?)";

    private RouteMapper mapper = new RouteMapper();

    @Override
    public boolean save(Route entity) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Optional<Route> findById(int id) {
        Optional<Route> result = Optional.empty();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROUTE_BY_ID_QUERY)) {
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
    public List<Route> findAll() {
        List<Route> result = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ROUTES_QUERY);
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void update(Route entity) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Route> findNotAppointRoutes(LocalDate date) {
        List<Route> result = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_NOT_APPOINT_ROUTE_QUERY)) {
            preparedStatement.setDate(1, Date.valueOf(date));
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
