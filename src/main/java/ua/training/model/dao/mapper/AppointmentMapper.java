package ua.training.model.dao.mapper;

import ua.training.model.entity.Appointment;
import ua.training.model.entity.AppointmentStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AppointmentMapper implements ObjectMapper<Appointment> {
    @Override
    public Appointment extractFromResultSet(ResultSet rs) throws SQLException {
        Appointment result = new Appointment();
        result.setId(rs.getInt("id"));
        result.setRouteId(rs.getInt("route_id"));
        result.setBusId(rs.getInt("bus_id"));
        result.setDriverId(rs.getInt("driver_id"));
        result.setDate(rs.getDate("date").toLocalDate());
        result.setStatus(AppointmentStatus.valueOf(rs.getString("status")));
        return result;
    }

    @Override
    public Appointment makeUnique(Map<Integer, Appointment> cache, Appointment teacher) {
        return null;
    }
}
