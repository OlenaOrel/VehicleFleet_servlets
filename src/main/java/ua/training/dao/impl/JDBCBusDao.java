package ua.training.dao.impl;

import ua.training.dao.BusDao;
import ua.training.dao.mapper.BusMapper;
import ua.training.entity.Bus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBusDao implements BusDao {
    private Connection connection;
    private BusMapper mapper = new BusMapper();

    public JDBCBusDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bus save(Bus entity) {
        return null;
    }

    @Override
    public Optional<Bus> findById(int id) {
        return null;
    }

    @Override
    public List<Bus> findAll() {
        List<Bus> result = new ArrayList<>();
        final String query = "SELECT * FROM bus";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
//            close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
