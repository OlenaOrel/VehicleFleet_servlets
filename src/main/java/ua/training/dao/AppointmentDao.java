package ua.training.dao;

import ua.training.entity.Appointment;
import ua.training.entity.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentDao extends GenericDao<Appointment> {

    Optional<Appointment> findAppointmentForDriver(LocalDate date, int driverId);

    void updateStatusByAppointmentId(AppointmentStatus status, int id);

    List<Appointment> findNotFinishedAppointment();

    int countAppointments();

    List<Appointment> findAllAppointments(int offset, int pageSize);

}
