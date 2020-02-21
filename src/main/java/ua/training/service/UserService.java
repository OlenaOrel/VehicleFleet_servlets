package ua.training.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.training.dao.DaoFactory;
import ua.training.dao.UserDao;
import ua.training.dto.UserDto;
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
            LOGGER.warn(e.getMessage());
        }
        return result;
    }

    public boolean isPassCorrect(String inputPass, String userPass) {
        return BCrypt.checkpw(inputPass, userPass);
    }

    public Optional<User> getUserById(int driverId) {
        Optional<User> result = Optional.empty();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findById(driverId);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return result;
    }

    public UserDto convertUserToDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getRole());
    }

    public List<User> getNotAppointDriverForBus(int busId) {
        List<User> result = new ArrayList<>();
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findNotAppointDriverForBus(busId);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return result;
    }

    public void saveUser(User user) throws UserExistException {
        UserDao userDao = daoFactory.createUserDao();
        userDao.saveUser(user);
    }
}