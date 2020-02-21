package ua.training.dao.impl;

import ua.training.dao.*;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new JDBSUserDao();
    }

    @Override
    public RouteDao createRouteDao() {
        return new JDBCRouteDao();
    }

    @Override
    public BusDao createBusDao() {
        return new JDBCBusDao();
    }

    @Override
    public AppointmentDao createAppointmentDao() {
        return new JDBCAppointmentDao();
    }

}
