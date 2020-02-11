package ua.training.dao.impl;

import ua.training.dao.BusDao;
import ua.training.dao.DaoFactory;
import ua.training.dao.RouteDao;
import ua.training.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBSUserDao(getConnection());
    }

    @Override
    public RouteDao createRouteDao() {
        return new JDBCRouteDao(getConnection());
    }

    @Override
    public BusDao createBusDao() {
        return new JDBCBusDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}