package ua.training.entity.builder;

import ua.training.entity.Bus;
import ua.training.entity.User;

import java.util.List;

public class BusBuilder {

    private Bus busBuilder;

    public BusBuilder() {
        busBuilder = new Bus();
    }

    public BusBuilder id(int id) {
        busBuilder.setId(id);
        return this;
    }

    public BusBuilder mark(String mark) {
        busBuilder.setMark(mark);
        return this;
    }

    public BusBuilder licensePlate(String licensePlate) {
        busBuilder.setLicensePlate(licensePlate);
        return this;
    }

    public BusBuilder drivers(List<User> drivers) {
        busBuilder.setDrivers(drivers);
        return this;
    }

    public Bus build() {
        return busBuilder;
    }
}


