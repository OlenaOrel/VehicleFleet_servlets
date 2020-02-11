package ua.training.dao.impl;

import ua.training.dao.RouteDao;
import ua.training.dao.mapper.RouteMapper;
import ua.training.entity.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public Route save(Route entity) {
        return null;
    }

    @Override
    public Optional<Route> findById(int id) {
        return null;
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
        } catch (SQLException e) {
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
