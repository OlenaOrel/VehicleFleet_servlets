package ua.training.dto;

import ua.training.entity.AppointmentStatus;

import java.time.LocalDate;

public class AppointmentDto {

    private int routeNumber;
    private String routeDeparture;
    private String routeDepartureUk;
    private String routeArrival;
    private String routeArrivalUk;
    private String busLicensePlate;
    private String busMark;
    private String driverFullName;
    private String driverFullNameUk;
    private LocalDate date;
    private AppointmentStatus status;

    public AppointmentDto() {
    }

    public AppointmentDto(int routeNumber, String routeDeparture,
                          String routeDepartureUk, String routeArrival,
                          String routeArrivalUk, String busLicensePlate,
                          String busMark, String driverFullName,
                          String driverFullNameUk, LocalDate date,
                          AppointmentStatus status) {
        this.routeNumber = routeNumber;
        this.routeDeparture = routeDeparture;
        this.routeDepartureUk = routeDepartureUk;
        this.routeArrival = routeArrival;
        this.routeArrivalUk = routeArrivalUk;
        this.busLicensePlate = busLicensePlate;
        this.busMark = busMark;
        this.driverFullName = driverFullName;
        this.driverFullNameUk = driverFullNameUk;
        this.date = date;
        this.status = status;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getRouteDeparture() {
        return routeDeparture;
    }

    public void setRouteDeparture(String routeDeparture) {
        this.routeDeparture = routeDeparture;
    }

    public String getRouteDepartureUk() {
        return routeDepartureUk;
    }

    public void setRouteDepartureUk(String routeDepartureUk) {
        this.routeDepartureUk = routeDepartureUk;
    }

    public String getRouteArrival() {
        return routeArrival;
    }

    public void setRouteArrival(String routeArrival) {
        this.routeArrival = routeArrival;
    }

    public String getRouteArrivalUk() {
        return routeArrivalUk;
    }

    public void setRouteArrivalUk(String routeArrivalUk) {
        this.routeArrivalUk = routeArrivalUk;
    }

    public String getBusLicensePlate() {
        return busLicensePlate;
    }

    public void setBusLicensePlate(String busLicensePlate) {
        this.busLicensePlate = busLicensePlate;
    }

    public String getBusMark() {
        return busMark;
    }

    public void setBusMark(String busMark) {
        this.busMark = busMark;
    }

    public String getDriverFullName() {
        return driverFullName;
    }

    public void setDriverFullName(String driverFullName) {
        this.driverFullName = driverFullName;
    }

    public String getDriverFullNameUk() {
        return driverFullNameUk;
    }

    public void setDriverFullNameUk(String driverFullNameUk) {
        driverFullNameUk = driverFullNameUk;
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
    public String toString() {
        return "AppointmentDto{" +
                "routeNumber=" + routeNumber +
                ", routeDeparture='" + routeDeparture + '\'' +
                ", routeDepartureUk='" + routeDepartureUk + '\'' +
                ", routeArrival='" + routeArrival + '\'' +
                ", routeArrivalUk='" + routeArrivalUk + '\'' +
                ", busLicensePlate='" + busLicensePlate + '\'' +
                ", busMark='" + busMark + '\'' +
                ", driverFullName='" + driverFullName + '\'' +
                ", driverFullNameUk='" + driverFullNameUk + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
