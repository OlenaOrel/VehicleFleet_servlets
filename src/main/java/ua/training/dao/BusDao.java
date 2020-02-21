package ua.training.dao;

import ua.training.entity.Bus;

import java.time.LocalDate;
import java.util.List;

public interface BusDao extends GenericDao<Bus> {

    List<Bus> findNotAppointBus(LocalDate date);
}
