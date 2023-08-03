package com.itblee.dao;

import java.util.List;

public interface GenericDAO<T> {
    List<T> findAll();
    T findByID(int id);
    Integer save(T entity);
    boolean update(T entity);
    boolean delete(int id);
}
