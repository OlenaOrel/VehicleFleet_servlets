package ua.training.model.entity;

import java.util.Objects;

public class Route {
    private int id;
    private int number;
    private String departureFromCityEn;
    private String arrivalToCityEn;
    private String departureFromCityUk;
    private String arrivalToCityUk;

    public Route() {
    }

    public Route(
            int id,
            int number,
            String departureFromCityEn,
            String arrivalToCityEn,
            String departureFromCityUk,
            String arrivalToCityUk) {
        this.id = id;
        this.number = number;
        this.departureFromCityEn = departureFromCityEn;
        this.arrivalToCityEn = arrivalToCityEn;
        this.departureFromCityUk = departureFromCityUk;
        this.arrivalToCityUk = arrivalToCityUk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id &&
                Objects.equals(number, route.number) &&
                Objects.equals(departureFromCityEn, route.departureFromCityEn) &&
                Objects.equals(arrivalToCityEn, route.arrivalToCityEn) &&
                Objects.equals(departureFromCityUk, route.departureFromCityUk) &&
                Objects.equals(arrivalToCityUk, route.arrivalToCityUk);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", number=" + number +
                ", departureFromCityEn='" + departureFromCityEn + '\'' +
                ", arrivalToCityEn='" + arrivalToCityEn + '\'' +
                ", departureFromCityUk='" + departureFromCityUk + '\'' +
                ", arrivalToCityUk='" + arrivalToCityUk + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, departureFromCityEn, arrivalToCityEn,
                departureFromCityUk, arrivalToCityUk);
    }
}
