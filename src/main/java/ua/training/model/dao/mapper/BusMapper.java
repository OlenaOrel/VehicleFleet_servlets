package ua.training.model.dao.mapper;

import ua.training.model.entity.Bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BusMapper implements ObjectMapper<Bus> {
    private static final String BUS_ID = "buses.id";
    private static final String BUS_LICENCE_PLATE = "number_plate";
    private static final String BUS_MARK = "mark";
    private static final String BUS_FREE = "buses.free";

    @Override
    public Bus extractFromResultSet(ResultSet rs) throws SQLException {
        return new Bus.Builder()
                .id(rs.getInt(BUS_ID))
                .mark(rs.getString(BUS_MARK))
                .licensePlate(rs.getString(BUS_LICENCE_PLATE))
                .build();
    }

    @Override
    public Bus makeUnique(Map<Integer, Bus> cache, Bus bus) {
        cache.putIfAbsent(bus.getId(), bus);
        return cache.get(bus.getId());
    }
}
