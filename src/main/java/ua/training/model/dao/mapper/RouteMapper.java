package ua.training.model.dao.mapper;

import ua.training.model.entity.Route;
import ua.training.model.entity.RouteStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RouteMapper implements ObjectMapper<Route> {
    private static final String ROUTE_ID = "routes.id";
    private static final String ROUTE_NUMBER = "number_of_route";
    private static final String ROUTE_DEPARTURE_FROM_CITY = "departure_from_city";
    private static final String ROUTE_DEPARTURE_FROM_CITY_UK = "departure_from_city_uk";
    private static final String ROUTE_ARRIVAL_TO_CITY = "arrival_to_city";
    private static final String ROUTE_ARRIVAL_TO_CITY_UK = "arrival_to_city_uk";
    private static final String ROUTE_DATE = "date";
    private static final String ROUTE_STATUS = "status";
    private static final String ROUTE_FINISHED = "finished";

    @Override
    public Route extractFromResultSet(ResultSet rs) throws SQLException {
        return new Route.Builder()
                .id(rs.getInt(ROUTE_ID))
                .numberOfRoute(rs.getInt(ROUTE_NUMBER))
                .departureFromCityEn(rs.getString(ROUTE_DEPARTURE_FROM_CITY))
                .arrivalToCityEn(rs.getString(ROUTE_ARRIVAL_TO_CITY))
                .departureFromCityUk(rs.getString(ROUTE_DEPARTURE_FROM_CITY_UK))
                .arrivalToCityUk(rs.getString(ROUTE_ARRIVAL_TO_CITY_UK))
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