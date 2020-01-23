package ua.training.model.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Route {
    private Long id;
    private Integer numberOfRoute;
    private String departureFromCityEn;
    private String arrivalToCityEn;
    private String departureFromCityUk;
    private String arrivalToCityUk;
    private LocalDate date;
    private Boolean finished;

    private Set<User> drivers;
    private Set<Bus> busList;

    public Route() {
    }

    public Route(
            Long id,
            Integer numberOfRoute,
            String departureFromCityEn,
            String arrivalToCityEn,
            String departureFromCityUk,
            String arrivalToCityUk,
            LocalDate date,
            Boolean finished,
            Set<User> drivers,
            Set<Bus> busList) {
        this.id = id;
        this.numberOfRoute = numberOfRoute;
        this.departureFromCityEn = departureFromCityEn;
        this.arrivalToCityEn = arrivalToCityEn;
        this.departureFromCityUk = departureFromCityUk;
        this.arrivalToCityUk = arrivalToCityUk;
        this.date = date;
        this.finished = finished;
        this.drivers = drivers;
        this.busList = busList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<User> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<User> drivers) {
        this.drivers = drivers;
    }

    public Set<Bus> getBusList() {
        return busList;
    }

    public void setBusList(Set<Bus> busList) {
        this.busList = busList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) &&
                Objects.equals(numberOfRoute, route.numberOfRoute) &&
                Objects.equals(departureFromCityEn, route.departureFromCityEn) &&
                Objects.equals(arrivalToCityEn, route.arrivalToCityEn) &&
                Objects.equals(departureFromCityUk, route.departureFromCityUk) &&
                Objects.equals(arrivalToCityUk, route.arrivalToCityUk) &&
                Objects.equals(date, route.date) &&
                Objects.equals(finished, route.finished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfRoute, departureFromCityEn, arrivalToCityEn, departureFromCityUk, arrivalToCityUk, date, finished);
    }

    public static class Builder {
        private Route routeBuilder;

        public Builder() {
            routeBuilder = new Route();
        }

        public Builder id(Long id) {
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

        public Builder drivers(Set<User> drivers) {
            routeBuilder.drivers = drivers;
            return this;
        }

        public Builder driver(User driver) {
            routeBuilder.drivers.add(driver);
            return this;
        }

        public Builder busList(Set<Bus> busList) {
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
