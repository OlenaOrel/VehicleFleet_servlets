package ua.training.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.AppointmentDao;
import ua.training.dao.mapper.AppointmentMapper;
import ua.training.entity.Appointment;
import ua.training.entity.AppointmentStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCAppointmentDao implements AppointmentDao {

    private static final Logger LOGGER = LogManager.getLogger(JDBCAppointmentDao.class);

    private static final String SAVE_APPOINTMENT = "INSERT INTO vehicle_fleet.appointment " +
            "(`route_id`, `bus_id`, `driver_id`, `date`, `status`) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_ALL_APPOINTMENT = "SELECT * FROM appointment";
    private static final String FIND_NOT_FINISHED_APPOINTMENT = "SELECT * FROM appointment WHERE status NOT IN ('" + AppointmentStatus.FINISHED.name() + "')";
    private static final String FIND_APPOINTMENT_FOR_DRIVER = "SELECT appointment.id, route_id, bus_id, driver_id, date, status " +
            "FROM appointment " +
            "LEFT JOIN user " +
            "ON appointment.driver_id = user.id " +
            "WHERE email = ? AND date = ? AND status = 'NEW'";
    private static final String UPDATE_APPOINTMENT_STATUS_BY_ID = "UPDATE appointment SET status = ? WHERE id = ?";
    private static final String COUNT_ALL_APPOINTMENT = "SELECT COUNT(*) AS total FROM appointment";
    private static final String FIND_ALL_APPOINTMENTS_FOR_PAGE = "SELECT * FROM appointment" +
            " ORDER BY date DESC" +
            " LIMIT ?, ?";

    private AppointmentMapper mapper = new AppointmentMapper();

    @Override
    public boolean save(Appointment entity) {
        boolean result = false;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_APPOINTMENT)) {
            preparedStatement.setInt(1, entity.getRouteId());
            preparedStatement.setInt(2, entity.getBusId());
            preparedStatement.setInt(3, entity.getDriverId());
            preparedStatement.setDate(4, Date.valueOf(entity.getDate()));
            preparedStatement.setString(5, entity.getStatus().name());
            result = preparedStatement.execute();
            return result;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Optional<Appointment> findById(int id) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> result = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_APPOINTMENT);
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Appointment> findNotFinishedAppointment() {
        List<Appointment> result = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_NOT_FINISHED_APPOINTMENT);
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public int countAppointments() {
        int result = 0;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT_ALL_APPOINTMENT);
            if (resultSet.next()) {
                result = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Appointment> findAllAppointments(int offset, int pageSize) {
        List<Appointment> result = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_APPOINTMENTS_FOR_PAGE)) {
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void update(Appointment entity) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Optional<Appointment> findAppointmentForDriver(String email) {
        LocalDate date = LocalDate.now();
        Optional<Appointment> result = Optional.empty();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_APPOINTMENT_FOR_DRIVER)) {
            preparedStatement.setString(1, email);
            preparedStatement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
            LOGGER.info("Appoint for driver: {}", result);
            return result;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void updateStatusByAppointmentId(AppointmentStatus status, int id) {
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT_STATUS_BY_ID)) {
            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Update appointment status");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
