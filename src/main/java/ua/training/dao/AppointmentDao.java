package ua.training.dao;

import ua.training.entity.Appointment;
import ua.training.entity.AppointmentStatus;

import java.util.Optional;

public interface AppointmentDao extends GenericDao<Appointment> {

    Optional<Appointment> findAppointmentForDriver(String email);

    void updateStatusByAppointmentId(AppointmentStatus status, int id);
}
