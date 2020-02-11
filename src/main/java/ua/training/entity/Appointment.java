package ua.training.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Appointment {

    int id;
    int routeId;
    int busId;
    int driverId;
    LocalDate date;
    AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(int id, int routeId, int busId, int driverId, LocalDate date, AppointmentStatus status) {
        this.id = id;
        this.routeId = routeId;
        this.busId = busId;
        this.driverId = driverId;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id &&
                routeId == that.routeId &&
                busId == that.busId &&
                driverId == that.driverId &&
                Objects.equals(date, that.date) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeId, busId, driverId, date, status);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", busId=" + busId +
                ", driverId=" + driverId +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
