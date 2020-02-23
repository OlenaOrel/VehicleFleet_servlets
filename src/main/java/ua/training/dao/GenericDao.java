package ua.training.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    boolean save(T entity);

    Optional<T> findById(int id);

    List<T> findAll();

    void update(T entity);
}
