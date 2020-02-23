package ua.training.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.BusDao;
import ua.training.dao.mapper.BusMapper;
import ua.training.entity.Bus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBusDao implements BusDao {

    private static final Logger LOGGER = LogManager.getLogger(JDBCBusDao.class);

    private static final String FIND_BUS_BY_ID_QUERY =
            "SELECT * FROM bus WHERE id =?";
    private static final String FIND_ALL_BUSES_QUERY =
            "SELECT * FROM bus";
    private static final String FIND_NOT_APPOINT_BUS_QUERY =
            "SELECT * FROM bus WHERE id " +
                    "NOT IN (SELECT bus_id FROM appointment WHERE date = ?)";

    private BusMapper mapper;

    public JDBCBusDao() {
        mapper = new BusMapper();
    }

    @Override
    public boolean save(Bus entity) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Optional<Bus> findById(int id) {
        Optional<Bus> result = Optional.empty();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BUS_BY_ID_QUERY)) {
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
    public List<Bus> findAll() {
        List<Bus> result = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BUSES_QUERY);
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void update(Bus entity) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public List<Bus> findNotAppointBus(LocalDate date) {
        List<Bus> result = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_NOT_APPOINT_BUS_QUERY)) {
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
