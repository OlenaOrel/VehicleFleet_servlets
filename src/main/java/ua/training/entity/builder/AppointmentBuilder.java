package ua.training.entity.builder;

import ua.training.entity.Appointment;
import ua.training.entity.AppointmentStatus;

import java.time.LocalDate;

public class AppointmentBuilder {

    private Appointment builder;

    public AppointmentBuilder() {
        builder = new Appointment();
    }

    public AppointmentBuilder id(int id) {
        builder.setId(id);
        return this;
    }

    public AppointmentBuilder routeId(int routeId) {
        builder.setRouteId(routeId);
        return this;
    }

    public AppointmentBuilder busId(int busId) {
        builder.setBusId(busId);
        return this;
    }

    public AppointmentBuilder driverId(int driverId) {
        builder.setDriverId(driverId);
        return this;
    }

    public AppointmentBuilder date(LocalDate date) {
        builder.setDate(date);
        return this;
    }

    public AppointmentBuilder status(AppointmentStatus status) {
        builder.setStatus(status);
        return this;
    }

    public Appointment build() {
        return builder;
    }

}
