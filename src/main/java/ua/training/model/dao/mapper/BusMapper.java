package ua.training.model.dao.mapper;

import ua.training.model.entity.Bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BusMapper implements ObjectMapper<Bus> {
    @Override
    public Bus extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Bus makeUnique(Map<Integer, Bus> cache, Bus teacher) {
        return null;
    }
}
