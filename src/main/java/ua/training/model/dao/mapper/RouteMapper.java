package ua.training.model.dao.mapper;

import ua.training.model.entity.Route;
import ua.training.model.entity.RouteStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ua.training.constants.DBColumnLabels.*;

public class RouteMapper implements ObjectMapper<Route> {
    @Override
    public Route extractFromResultSet(ResultSet rs) throws SQLException {
        return new Route.Builder()
                .id(rs.getInt(ROUTE_ID))
                .numberOfRoute(rs.getInt(ROUTE_NUMBER))
                .departureFromCityEn(rs.getString(ROUTE_DEPARTURE_FROM_CITY))
                .arrivalToCityEn(rs.getString(ROUTE_ARRIVAL_TO_CITY))
                .departureFromCityUk(rs.getString(ROUTE_DEPARTURE_FROM_CITY_UK))
                .arrivalToCityUk(rs.getString(ROUTE_DEPARTURE_FROM_CITY_UK))
                .date(rs.getDate(ROUTE_DATE).toLocalDate())
                .finished(rs.getBoolean(ROUTE_FINISHED))
                .status(RouteStatus.valueOf(rs.getString(ROUTE_STATUS)))
                .build();
    }

    @Override
    public Route makeUnique(Map<Integer, Route> cache, Route route) {
        cache.putIfAbsent(route.getId(), route);
        return cache.get(route.getId());
    }
}
