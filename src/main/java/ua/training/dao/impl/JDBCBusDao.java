package ua.training.dao.impl;

import ua.training.dao.BusDao;
import ua.training.dao.mapper.BusMapper;
import ua.training.entity.Bus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBusDao implements BusDao {

    private static final String FIND_BUS_BY_ID_QUERY = "SELECT * FROM bus WHERE id =?";
    private static final String FIND_ALL_BUSES_QUERY = "SELECT * FROM bus";
    private static final String FIND_NOT_APPOINT_BUS_QUERY = "SELECT * FROM bus WHERE id " +
            "NOT IN (SELECT bus_id FROM appointment WHERE date = ?)";

    private Connection connection;
    private BusMapper mapper = new BusMapper();

    public JDBCBusDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Bus entity) {

    }

    @Override
    public Optional<Bus> findById(int id) {
        Optional<Bus> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BUS_BY_ID_QUERY)) {
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
    public List<Bus> findAll() {
        List<Bus> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BUSES_QUERY);
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

    public List<Bus> findNotAppointBus() {
        LocalDate date = LocalDate.now();
        List<Bus> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_NOT_APPOINT_BUS_QUERY)) {
            preparedStatement.setDate(1, Date.valueOf(date));
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
