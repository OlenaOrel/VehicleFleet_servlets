package ua.training.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.training.dao.DaoFactory;
import ua.training.dao.UserDao;
import ua.training.entity.User;

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
}