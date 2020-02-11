package ua.training.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    T save(T entity);
    Optional<T> findById(int id);
    List<T> findAll();
    void update(T entity);
}
