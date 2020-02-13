package ua.training.dao.impl;

import ua.training.dao.AppointmentDao;
import ua.training.entity.Appointment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCAppointmentDao implements AppointmentDao {

    private static final String SAVE_APPOINTMENT_QUERY = "INSERT INTO vehicle_fleet.appointment " +
            "(`route_id`, `bus_id`, `driver_id`, `date`, `status`) " +
            "VALUES (?, ?, ?, ?, ?)";

    private Connection connection;

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
            close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Appointment> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll() {
        return null;
    }

    @Override
    public void update(Appointment entity) {

    }

    @Override
    public void close() throws Exception {

    }
}
