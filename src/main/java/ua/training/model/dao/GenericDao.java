package ua.training.model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    T save(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
}
