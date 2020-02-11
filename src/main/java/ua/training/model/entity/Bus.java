package ua.training.model.entity;

import java.util.List;
import java.util.Objects;

public class Bus {
    private int id;
    private String mark;
    private String licensePlate;

    private List<User> drivers;

    public Bus() {
    }

    public Bus(
            int id,
            String mark,
            String licensePlate,
            List<User> drivers) {
        this.id = id;
        this.mark = mark;
        this.licensePlate = licensePlate;
        this.drivers = drivers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<User> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<User> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return Objects.equals(id, bus.id) &&
                Objects.equals(mark, bus.mark) &&
                Objects.equals(licensePlate, bus.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, licensePlate);
    }

    public static class Builder {
        private Bus busBuilder;

        public Builder() {
            busBuilder = new Bus();
        }

        private Long id;
        private String mark;
        private String licensePlate;

        private List<User> drivers;

        public Builder id(int id) {
            busBuilder.id = id;
            return this;
        }

        public Builder mark(String mark) {
            busBuilder.mark = mark;
            return this;
        }

        public Builder licensePlate(String licensePlate) {
            busBuilder.licensePlate = licensePlate;
            return this;
        }

        public Builder drivers(List<User> drivers) {
            busBuilder.drivers = drivers;
            return this;
        }

        public Builder driver(User driver) {
            busBuilder.drivers.add(driver);
            return this;
        }

        public Bus build() {
            return busBuilder;
        }
    }
}
