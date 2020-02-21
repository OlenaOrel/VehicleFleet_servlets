package ua.training.dao;

import ua.training.entity.Route;

import java.time.LocalDate;
import java.util.List;

public interface RouteDao extends GenericDao<Route> {

    List<Route> findNotAppointRoutes(LocalDate date);
}
