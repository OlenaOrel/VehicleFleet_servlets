package ua.training.constants;

import java.util.ResourceBundle;

public interface DBColumnLabels {
    ResourceBundle bundle = ResourceBundle.getBundle("db_column_label");


    String USER_ID = bundle.getString("user.id");
    String USER_FIRST_NAME = bundle.getString("user.first.name");
    String USER_LAST_NAME = bundle.getString("user.last.name");
    String USER_ORIGIN_FIRST_NAME = bundle.getString("user.origin.first.name");
    String USER_ORIGIN_LAST_NAME = bundle.getString("user.origin.last.name");
    String USER_LOGIN = bundle.getString("user.login");
    String USER_EMAIL = bundle.getString("user.email");
    String USER_PASSWORD = bundle.getString("user.password");
    String USER_ROLE = bundle.getString("user.role");
    String USER_FREE = bundle.getString("user.free");

    String ROUTE_ID = bundle.getString("route.id");
    String ROUTE_NUMBER = bundle.getString("route.number");
    String ROUTE_DEPARTURE_FROM_CITY = bundle.getString("route.departure.from.city");
    String ROUTE_DEPARTURE_FROM_CITY_UK = bundle.getString("route.departure.from.city.uk");
    String ROUTE_ARRIVAL_TO_CITY = bundle.getString("route.arrival.from.city");
    String ROUTE_ARRIVAL_TO_CITY_UK = bundle.getString("route.arrival.from.city.uk");
    String ROUTE_DATE = bundle.getString("route.date");
    String ROUTE_STATUS = bundle.getString("route.status");
    String ROUTE_FINISHED = bundle.getString("route.finished");

    String BUS_ID = bundle.getString("bus.id");
    String BUS_LICENCE_PLATE = bundle.getString("bus.licence.plate");
    String BUS_MARK = bundle.getString("bus.mark");
    String BUS_FREE = bundle.getString("bus.free");
}
