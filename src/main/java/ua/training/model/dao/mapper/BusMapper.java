package ua.training.model.dao.mapper;

import ua.training.model.entity.Bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static ua.training.constants.DBColumnLabels.*;

public class BusMapper implements ObjectMapper<Bus> {
    @Override
    public Bus extractFromResultSet(ResultSet rs) throws SQLException {
        return new Bus.Builder()
                .id(rs.getInt(BUS_ID))
                .mark(rs.getString(BUS_MARK))
                .licensePlate(rs.getString(BUS_LICENCE_PLATE))
                .free(rs.getBoolean(BUS_FREE))
                .build();
    }

    @Override
    public Bus makeUnique(Map<Integer, Bus> cache, Bus bus) {
        cache.putIfAbsent(bus.getId(), bus);
        return cache.get(bus.getId());
    }
}
