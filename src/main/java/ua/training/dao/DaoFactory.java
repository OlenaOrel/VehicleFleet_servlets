package ua.training.dao;

import ua.training.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract RouteDao createRouteDao();

    public abstract BusDao createBusDao();

    public abstract AppointmentDao createAppointmentDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
