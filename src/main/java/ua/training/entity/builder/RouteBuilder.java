package ua.training.entity.builder;

import ua.training.entity.Route;

public class RouteBuilder {

    private Route routeBuilder;

    public RouteBuilder() {
        routeBuilder = new Route();
    }

    public RouteBuilder id(int id) {
        routeBuilder.setId(id);
        return this;
    }

    public RouteBuilder numberOfRoute(Integer numberOfRoute) {
        routeBuilder.setNumber(numberOfRoute);
        return this;
    }

    public RouteBuilder departureFromCityEn(String departureFromCityEn) {
        routeBuilder.setDepartureFromCityEn(departureFromCityEn);
        return this;
    }

    public RouteBuilder arrivalToCityEn(String arrivalToCityEn) {
        routeBuilder.setArrivalToCityEn(arrivalToCityEn);
        return this;
    }

    public RouteBuilder departureFromCityUk(String departureFromCityUk) {
        routeBuilder.setDepartureFromCityUk(departureFromCityUk);
        return this;
    }

    public RouteBuilder arrivalToCityUk(String arrivalToCityUk) {
        routeBuilder.setArrivalToCityUk(arrivalToCityUk);
        return this;
    }

    public Route build() {
        return routeBuilder;
    }
}
