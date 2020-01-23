package ua.training.model.dao.impl;

import ua.training.model.dao.BusDao;
import ua.training.model.entity.Bus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCBusDao implements BusDao {
    private Connection connection;

    public JDBCBusDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bus save(Bus entity) {
        return null;
    }

    @Override
    public Bus findById(int id) {
        return null;
    }

    @Override
    public List<Bus> findAll() {
        return null;
    }

    @Override
    public void update(Bus entity) {

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
