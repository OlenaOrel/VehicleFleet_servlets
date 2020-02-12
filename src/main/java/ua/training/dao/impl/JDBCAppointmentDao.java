package ua.training.dao.impl;

import ua.training.dao.AppointmentDao;
import ua.training.entity.Appointment;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCAppointmentDao implements AppointmentDao {

    private Connection connection;

    public JDBCAppointmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Appointment entity) {
        String query = "INSERT INTO vehicle_fleet.appointment (`route_id`, `bus_id`, `driver_id`, `date`, `status`) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, entity.getRouteId());
            preparedStatement.setInt(2, entity.getBusId());
            preparedStatement.setInt(3, entity.getDriverId());
            preparedStatement.setDate(4, Date.valueOf(entity.getDate()));
            preparedStatement.setString(5, entity.getStatus().name());
            preparedStatement.execute();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
