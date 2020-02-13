package ua.training.dao;

import ua.training.entity.Bus;

import java.util.List;

public interface BusDao extends GenericDao<Bus> {

    public List<Bus> findNotAppointBus();
}
