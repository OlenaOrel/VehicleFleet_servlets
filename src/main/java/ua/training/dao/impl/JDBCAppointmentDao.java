package ua.training.dao.impl;

import ua.training.dao.AppointmentDao;
import ua.training.entity.Appointment;

import java.util.List;
import java.util.Optional;

public class JDBCAppointmentDao implements AppointmentDao {

    @Override
    public Appointment save(Appointment entity) {
        return null;
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