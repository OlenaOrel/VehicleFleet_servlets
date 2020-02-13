package ua.training.dao;

import ua.training.entity.User;
import ua.training.exception.UserExistException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);

    List<User> findByBuses_id(int busId);

    List<User> findNotAppointDriverForBus(int busId);

    void saveUser(User entity) throws UserExistException;
}
