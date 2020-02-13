package ua.training.dao;

import ua.training.entity.Route;

import java.util.List;

public interface RouteDao extends GenericDao<Route> {

    List<Route> findNotAppointRoutes();
}
