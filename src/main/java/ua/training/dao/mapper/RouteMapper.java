package ua.training.dao.mapper;

import ua.training.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements ObjectMapper<Route> {
    private static final String ROUTE_ID = "route.id";
    private static final String ROUTE_NUMBER = "number";
    private static final String ROUTE_DEPARTURE_FROM_CITY = "departure_from_city";
    private static final String ROUTE_DEPARTURE_FROM_CITY_UK = "departure_from_city_uk";
    private static final String ROUTE_ARRIVAL_TO_CITY = "arrival_to_city";
    private static final String ROUTE_ARRIVAL_TO_CITY_UK = "arrival_to_city_uk";

    @Override
    public Route extractFromResultSet(ResultSet rs) throws SQLException {
        return new Route.Builder()
                .id(rs.getInt(ROUTE_ID))
                .numberOfRoute(rs.getInt(ROUTE_NUMBER))
                .departureFromCityEn(rs.getString(ROUTE_DEPARTURE_FROM_CITY))
                .arrivalToCityEn(rs.getString(ROUTE_ARRIVAL_TO_CITY))
                .departureFromCityUk(rs.getString(ROUTE_DEPARTURE_FROM_CITY_UK))
                .arrivalToCityUk(rs.getString(ROUTE_ARRIVAL_TO_CITY_UK))
                .build();
    }

}
