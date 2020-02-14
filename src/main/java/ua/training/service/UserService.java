package ua.training.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.training.dao.DaoFactory;
import ua.training.dao.UserDao;
import ua.training.entity.User;
import ua.training.exception.UserExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> getUserByEmail(String email) {
        Optional<User> result = Optional.empty();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isPassCorrect(String inputPass, String userPass) {
        return BCrypt.checkpw(inputPass, userPass);
    }

    public List<User> getAllByBusId(int id) {
        List<User> result = new ArrayList<>();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByBuses_id(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Optional<User> getUserById(int driverId) {
        Optional<User> result = Optional.empty();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findById(driverId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> getNotAppointDriverForBus(int busId) {
        List<User> result = new ArrayList<>();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findNotAppointDriverForBus(busId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveUser(User user) throws UserExistException {
        UserDao userDao = daoFactory.createUserDao();
        userDao.saveUser(user);
    }
}