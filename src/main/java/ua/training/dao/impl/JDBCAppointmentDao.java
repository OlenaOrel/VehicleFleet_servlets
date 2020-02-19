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

    private static final String SAVE_APPOINTMENT_QUERY = "INSERT INTO vehicle_fleet.appointment " +
            "(`route_id`, `bus_id`, `driver_id`, `date`, `status`) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_ALL_APPOINTMENT = "SELECT * FROM appointment";
    private static final String FIND_NOT_FINISHED_APPOINTMENT = "SELECT * FROM appointment WHERE status NOT IN ('" + AppointmentStatus.FINISHED.name() + "')";
    private static final String FIND_APPOINTMENT_FOR_DRIVER = "SELECT appointment.id, route_id, bus_id, driver_id, date, status " +
            "FROM appointment " +
            "LEFT JOIN user " +
            "ON appointment.driver_id = user.id " +
            "WHERE email = ? AND date = ? AND status = 'NEW'";
    private static final String UPDATE_APPOINTMENT_STATUS_BY_ID_QUERY = "UPDATE appointment SET status = ? WHERE id = ?";
    private static final String FIND_ID_BY_STATUS_AND_ROUTE_NUMBER_QUERY = "SELECT appointment.id FROM appointment " +
            "LEFT JOIN route ON appointment.route_id = route.id " +
            "WHERE status = ? AND number = ?";

    private Connection connection;
    private AppointmentMapper mapper = new AppointmentMapper();

    public JDBCAppointmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Appointment entity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_APPOINTMENT_QUERY)) {
            preparedStatement.setInt(1, entity.getRouteId());
            preparedStatement.setInt(2, entity.getBusId());
            preparedStatement.setInt(3, entity.getDriverId());
            preparedStatement.setDate(4, Date.valueOf(entity.getDate()));
            preparedStatement.setString(5, entity.getStatus().name());
            result = preparedStatement.execute();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    @Override
    public Optional<Appointment> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_APPOINTMENT);
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    @Override
    public List<Appointment> findNotFinishedAppointment() {
        List<Appointment> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_NOT_FINISHED_APPOINTMENT);
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    @Override
    public void update(Appointment entity) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Appointment> findAppointmentForDriver(String email) {
        LocalDate date = LocalDate.now();
        Optional<Appointment> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_APPOINTMENT_FOR_DRIVER)) {
            preparedStatement.setString(1, email);
            preparedStatement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
            LOGGER.info("Appoint for driver: {}", result);
            return result;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        } finally {
            close();
        }
        return result;
    }

    @Override
    public void updateStatusByAppointmentId(AppointmentStatus status, int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT_STATUS_BY_ID_QUERY)) {
            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Update appointment status");
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public int findAppointmentIdByStatusAndRouteNumber(AppointmentStatus status, int routeNumber) {
        int result = -1;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ID_BY_STATUS_AND_ROUTE_NUMBER_QUERY)) {
            statement.setString(1, status.name());
            statement.setInt(2, routeNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("appointment.id");
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        } finally {
            close();
        }
        return result;
    }
}
