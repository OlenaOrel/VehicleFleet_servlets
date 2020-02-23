package ua.training.dao;

import ua.training.entity.User;
import ua.training.exception.UserExistException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByEmail(String email);

    List<User> findNotAppointDriverForBusByDate(LocalDate date, int busId);

    void saveUser(User entity) throws UserExistException;
}
