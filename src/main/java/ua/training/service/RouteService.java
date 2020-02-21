package ua.training.service;

import ua.training.dao.DaoFactory;
import ua.training.dao.RouteDao;
import ua.training.entity.Route;

import java.util.List;
import java.util.Optional;

public class RouteService {

    RouteDao routeDao = DaoFactory.getInstance().createRouteDao();

    public Optional<Route> getRouteById(int routeId) {
        return routeDao.findById(routeId);
    }

    public List<Route> getNotAppointRoute() {
        return routeDao.findNotAppointRoutes();
    }
}
