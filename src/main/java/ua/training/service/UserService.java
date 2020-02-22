package ua.training.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.training.dao.DaoFactory;
import ua.training.dao.UserDao;
import ua.training.dto.UserDto;
import ua.training.dto.UserRegisterDto;
import ua.training.entity.User;
import ua.training.entity.UserRole;
import ua.training.entity.builder.UserBuilder;
import ua.training.exception.UserExistException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private UserDao userDao;

    public UserService() {
        userDao = DaoFactory.getInstance().createUserDao();
    }

    public Optional<User> getUserByEmail(String email) {
        return userDao.findByEmail(email);

    }

    public boolean isPassCorrect(String inputPass, String userPass) {
        return BCrypt.checkpw(inputPass, userPass);
    }

    public Optional<User> getUserById(int driverId) {
        return userDao.findById(driverId);
    }

    public UserDto convertUserToDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getRole());
    }

    public boolean isPassNotConfirm(String pass, String confirmPass) {
        return !pass.equals(confirmPass);
    }

    public User createUserFromUserRegisterDto(UserRegisterDto userDto) {
        return new UserBuilder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .originFirstName(userDto.getOriginFirstName())
                .originLastName(userDto.getOriginLastName())
                .email(userDto.getEmail())
                .password(getSecurePass(userDto.getPassword()))
                .role(UserRole.ROLE_DRIVER)
                .build();
    }

    private String getSecurePass(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    public List<User> getNotAppointDriverForBus(int busId) {
        LocalDate date = LocalDate.now();
        return userDao.findNotAppointDriverForBusByDate(date, busId);
    }

    public void saveUser(User user) throws UserExistException {
        userDao.saveUser(user);
    }
}