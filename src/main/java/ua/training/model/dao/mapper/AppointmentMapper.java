package ua.training.model.dao.mapper;

import ua.training.model.entity.Appointment;
import ua.training.model.entity.AppointmentStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AppointmentMapper implements ObjectMapper<Appointment> {

    private static final String APPOINTMENT_ID = "id";
    private static final String APPOINTMENT_ROUTE_ID = "route_id";
    private static final String APPOINTMENT_BUS_ID = "bus_id";
    private static final String APPOINTMENT_DRIVER_ID = "driver_id";
    private static final String APPOINTMENT_DATE = "date";
    private static final String APPOINTMENT_STATUS = "status";



    @Override
    public Appointment extractFromResultSet(ResultSet rs) throws SQLException {
        Appointment result = new Appointment();
        result.setId(rs.getInt(APPOINTMENT_ID));
        result.setRouteId(rs.getInt(APPOINTMENT_ROUTE_ID));
        result.setBusId(rs.getInt(APPOINTMENT_BUS_ID));
        result.setDriverId(rs.getInt(APPOINTMENT_DRIVER_ID));
        result.setDate(rs.getDate(APPOINTMENT_DATE).toLocalDate());
        result.setStatus(AppointmentStatus.valueOf(rs.getString(APPOINTMENT_STATUS)));
        return result;
    }

    @Override
    public Appointment makeUnique(Map<Integer, Appointment> cache, Appointment teacher) {
        return null;
    }
}
