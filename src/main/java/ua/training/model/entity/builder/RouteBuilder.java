package ua.training.model.entity.builder;

import ua.training.model.entity.Route;

public class RouteBuilder {

    private Route routeBuilder;

    private int id;
    private int number;
    private String departureFromCityEn;
    private String arrivalToCityEn;
    private String departureFromCityUk;
    private String arrivalToCityUk;

    public RouteBuilder() {
        routeBuilder = new Route();
    }

    public void id(int id) {
        this.id = id;
    }

    public void number(int number) {
        this.number = number;
    }

    public void departureFromCityEn(String departureFromCityEn) {
        this.departureFromCityEn = departureFromCityEn;
    }

    public void arrivalToCityEn(String arrivalToCityEn) {
        this.arrivalToCityEn = arrivalToCityEn;
    }

    public void departureFromCityUk(String departureFromCityUk) {
        this.departureFromCityUk = departureFromCityUk;
    }

    public void arrivalToCityUk(String arrivalToCityUk) {
        this.arrivalToCityUk = arrivalToCityUk;
    }

    public Route build() {
        routeBuilder.setId(id);
        routeBuilder.setNumber(number);
        routeBuilder.setDepartureFromCityEn(departureFromCityEn);
        routeBuilder.setDepartureFromCityUk(departureFromCityUk);
        routeBuilder.setArrivalToCityEn(arrivalToCityEn);
        routeBuilder.setArrivalToCityUk(arrivalToCityUk);
        return routeBuilder;
    }
}


