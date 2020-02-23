package ua.training.service;

import ua.training.dao.BusDao;
import ua.training.dao.DaoFactory;
import ua.training.entity.Bus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BusService {

    private BusDao busDao;

    public BusService() {
        busDao = DaoFactory.getInstance().createBusDao();
    }

    public Optional<Bus> getBusById(int busId) {
        return busDao.findById(busId);
    }

    public List<Bus> getNotAppointBus() {
        return busDao.findNotAppointBus(LocalDate.now());
    }
}
