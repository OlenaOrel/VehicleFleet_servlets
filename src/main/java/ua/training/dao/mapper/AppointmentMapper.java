package ua.training.dao.mapper;

import ua.training.entity.Appointment;
import ua.training.entity.AppointmentStatus;
import ua.training.entity.builder.AppointmentBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper implements ObjectMapper<Appointment> {

    private static final String APPOINTMENT_ID = "id";
    private static final String APPOINTMENT_ROUTE_ID = "route_id";
    private static final String APPOINTMENT_BUS_ID = "bus_id";
    private static final String APPOINTMENT_DRIVER_ID = "driver_id";
    private static final String APPOINTMENT_DATE = "date";
    private static final String APPOINTMENT_STATUS = "status";


    @Override
    public Appointment extractFromResultSet(ResultSet rs) throws SQLException {
        return new AppointmentBuilder()
                .id(rs.getInt(APPOINTMENT_ID))
                .routeId(rs.getInt(APPOINTMENT_ROUTE_ID))
                .busId(rs.getInt(APPOINTMENT_BUS_ID))
                .driverId(rs.getInt(APPOINTMENT_DRIVER_ID))
                .date(rs.getDate(APPOINTMENT_DATE).toLocalDate())
                .status(AppointmentStatus.valueOf(rs.getString(APPOINTMENT_STATUS)))
                .build();
    }
}
