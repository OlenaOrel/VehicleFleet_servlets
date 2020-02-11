package ua.training.model.entity.builder;

import ua.training.model.entity.Bus;
import ua.training.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class BusBuilder {

    private Bus busBuilder;

    private int id;
    private String mark;
    private String licensePlate;
    private List<User> drivers = new ArrayList<>();

    public BusBuilder() {
        busBuilder = new Bus();
    }

    public void id(int id) {
        this.id = id;
    }

    public void mark(String mark) {
        this.mark = mark;
    }

    public void licensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void drivers(List<User> drivers) {
        this.drivers = drivers;
    }

    public void driver(User driver) {
        this.drivers.add(driver);
    }

    public Bus build() {
        busBuilder.setId(id);
        busBuilder.setMark(mark);
        busBuilder.setLicensePlate(licensePlate);
        busBuilder.setDrivers(drivers);
        return busBuilder;
    }
}
