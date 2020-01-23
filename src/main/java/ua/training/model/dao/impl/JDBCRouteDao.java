package ua.training.model.dao.impl;

import ua.training.model.dao.RouteDao;
import ua.training.model.entity.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCRouteDao implements RouteDao {
    private Connection connection;

    public JDBCRouteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Route save(Route entity) {
        return null;
    }

    @Override
    public Route findById(int id) {
        return null;
    }

    @Override
    public List<Route> findAll() {
        return null;
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
