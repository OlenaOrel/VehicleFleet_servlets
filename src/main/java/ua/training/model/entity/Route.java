package ua.training.model.entity;

import java.time.LocalDate;
import java.util.List;

public class Route {
    private int id;
    private Integer numberOfRoute;
    private String departureFromCityEn;
    private String arrivalToCityEn;
    private String departureFromCityUk;
    private String arrivalToCityUk;
    private LocalDate date;
    private Boolean finished;
    private RouteStatus status;

    private List<User> drivers;
    private List<Bus> busList;

    public Route() {
    }

    public Route(
            int id,
            Integer numberOfRoute,
            String departureFromCityEn,
            String arrivalToCityEn,
            String departureFromCityUk,
            String arrivalToCityUk,
            LocalDate date,
            Boolean finished,
            RouteStatus status,
            List<User> drivers,
            List<Bus> busList) {
        this.id = id;
        this.numberOfRoute = numberOfRoute;
        this.departureFromCityEn = departureFromCityEn;
        this.arrivalToCityEn = arrivalToCityEn;
        this.departureFromCityUk = departureFromCityUk;
        this.arrivalToCityUk = arrivalToCityUk;
        this.date = date;
        this.finished = finished;
        this.status = status;
        this.drivers = drivers;
        this.busList = busList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumberOfRoute() {
        return numberOfRoute;
    }

    public void setNumberOfRoute(Integer numberOfRoute) {
        this.numberOfRoute = numberOfRoute;
    }

    public String getDepartureFromCityEn() {
        return departureFromCityEn;
    }

    public void setDepartureFromCityEn(String departureFromCityEn) {
        this.departureFromCityEn = departureFromCityEn;
    }

    public String getArrivalToCityEn() {
        return arrivalToCityEn;
    }

    public void setArrivalToCityEn(String arrivalToCityEn) {
        this.arrivalToCityEn = arrivalToCityEn;
    }

    public String getDepartureFromCityUk() {
        return departureFromCityUk;
    }

    public void setDepartureFromCityUk(String departureFromCityUk) {
        this.departureFromCityUk = departureFromCityUk;
    }

    public String getArrivalToCityUk() {
        return arrivalToCityUk;
    }

    public void setArrivalToCityUk(String arrivalToCityUk) {
        this.arrivalToCityUk = arrivalToCityUk;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public RouteStatus getStatus() {
        return status;
    }

    public void setStatus(RouteStatus status) {
        this.status = status;
    }

    public List<User> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<User> drivers) {
        this.drivers = drivers;
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }



    public static class Builder {
        private Route routeBuilder;

        public Builder() {
            routeBuilder = new Route();
        }

        public Builder id(int id) {
            routeBuilder.id = id;
            return this;
        }

        public Builder numberOfRoute(Integer numberOfRoute) {
            routeBuilder.numberOfRoute = numberOfRoute;
            return this;
        }

        public Builder departureFromCityEn(String departureFromCityEn) {
            routeBuilder.departureFromCityEn = departureFromCityEn;
            return this;
        }

        public Builder arrivalToCityEn(String arrivalToCityEn) {
            routeBuilder.arrivalToCityEn = arrivalToCityEn;
            return this;
        }

        public Builder departureFromCityUk(String departureFromCityUk) {
            routeBuilder.departureFromCityUk = departureFromCityUk;
            return this;
        }

        public Builder arrivalToCityUk(String arrivalToCityUk) {
            routeBuilder.arrivalToCityUk = arrivalToCityUk;
            return this;
        }

        public Builder date(LocalDate date) {
            routeBuilder.date = date;
            return this;
        }

        public Builder finished(Boolean finished) {
            routeBuilder.finished = finished;
            return this;
        }

        public Builder finished(RouteStatus status) {
            routeBuilder.status = status;
            return this;
        }

        public Builder drivers(List<User> drivers) {
            routeBuilder.drivers = drivers;
            return this;
        }

        public Builder driver(User driver) {
            routeBuilder.drivers.add(driver);
            return this;
        }

        public Builder busList(List<Bus> busList) {
            routeBuilder.busList = busList;
            return this;
        }

        public Builder bus(Bus bus) {
            routeBuilder.busList.add(bus);
            return this;
        }

        public Route build() {
            return routeBuilder;
        }
    }

}
