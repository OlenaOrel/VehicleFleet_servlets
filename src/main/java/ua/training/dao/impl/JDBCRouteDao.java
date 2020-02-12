package ua.training.dao.impl;

import ua.training.dao.RouteDao;
import ua.training.dao.mapper.RouteMapper;
import ua.training.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCRouteDao implements RouteDao {
    private Connection connection;
    private RouteMapper mapper = new RouteMapper();

    public JDBCRouteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Route entity) {

    }

    @Override
    public Optional<Route> findById(int id) {
        final String query = "SELECT * FROM route WHERE id = ?";
        Optional<Route> result = Optional.empty();
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
    public List<Route> findAll() {
        List<Route> result = new ArrayList<>();
        final String query = "SELECT * FROM route";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
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
    public void update(Route entity) {

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
