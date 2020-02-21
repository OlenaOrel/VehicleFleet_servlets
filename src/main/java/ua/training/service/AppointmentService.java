package ua.training.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.AppointmentDao;
import ua.training.dao.DaoFactory;
import ua.training.entity.Appointment;
import ua.training.entity.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AppointmentService {

    private static final Logger LOGGER = LogManager.getLogger(AppointmentService.class);

    AppointmentDao appointmentDao = DaoFactory.getInstance().createAppointmentDao();

    public Appointment createAppointment(int routeId, int busId, int driverId) {
        LOGGER.info("Create appointment");
        Appointment result = new Appointment();
        LocalDate date = LocalDate.now();
        result.setRouteId(routeId);
        result.setBusId(busId);
        result.setDriverId(driverId);
        result.setDate(date);
        result.setStatus(AppointmentStatus.NEW);
        return result;
    }


    public void save(Appointment appointment) {
        LOGGER.info("Save appointment");
            appointmentDao.save(appointment);

    }

    public Optional<Appointment> getAppointmentForDriver(String email) {
        return appointmentDao.findAppointmentForDriver(email);
    }

    public void setStatusConfirm(Appointment appointment) {
        appointmentDao.updateStatusByAppointmentId(AppointmentStatus.CONFIRMED, appointment.getId());
    }

    public List<Appointment> getNotFinishedAppointment() {
        return appointmentDao.findNotFinishedAppointment();
    }

    public void doFinish(int appointmentId) {
        appointmentDao.updateStatusByAppointmentId(AppointmentStatus.FINISHED, appointmentId);
    }
}
