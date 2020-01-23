package ua.training.model.dao.mapper;

import ua.training.model.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RouteMapper implements ObjectMapper<Route> {
    @Override
    public Route extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Route makeUnique(Map<Integer, Route> cache, Route teacher) {
        return null;
    }
}
