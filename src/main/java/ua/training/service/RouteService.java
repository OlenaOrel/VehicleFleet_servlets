package ua.training.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.DaoFactory;
import ua.training.dao.RouteDao;
import ua.training.entity.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteService {

    private static final Logger LOGGER = LogManager.getLogger(RouteService.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Route> getAllRouts() {
        List<Route> result = new ArrayList<>();
        try(RouteDao routeDao = daoFactory.createRouteDao()) {
            result = routeDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
