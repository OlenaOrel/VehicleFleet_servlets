package ua.training.service;

import ua.training.dao.BusDao;
import ua.training.dao.DaoFactory;
import ua.training.entity.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Bus> getAllBuses() {
        List<Bus> result = new ArrayList<>();
        try(BusDao busDao = daoFactory.createBusDao()) {
            result = busDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Optional<Bus> getBusById(int busId) {
        Optional<Bus> result = Optional.empty();
        try(BusDao busDao = daoFactory.createBusDao()) {
            result = busDao.findById(busId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
