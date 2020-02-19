package ua.training.dao.mapper;

import ua.training.entity.Bus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusMapper implements ObjectMapper<Bus> {
    private static final String BUS_ID = "bus.id";
    private static final String BUS_LICENCE_PLATE = "license_plate";
    private static final String BUS_MARK = "mark";

    @Override
    public Bus extractFromResultSet(ResultSet rs) throws SQLException {
        return new Bus.Builder()
                .id(rs.getInt(BUS_ID))
                .mark(rs.getString(BUS_MARK))
                .licensePlate(rs.getString(BUS_LICENCE_PLATE))
                .build();
    }

}
