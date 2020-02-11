package ua.training.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.training.dao.DaoFactory;
import ua.training.dao.UserDao;
import ua.training.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

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
        try(UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByBuses_id(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}