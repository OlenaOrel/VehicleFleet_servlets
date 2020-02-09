package ua.training.model.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;

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