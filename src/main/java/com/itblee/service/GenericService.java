package com.itblee.service;

import java.util.List;
import java.util.Map;

public interface GenericService<T> {
    List<T> findAll();
    T findByID(int id);
    boolean isExist(T entity);
    int getTotalCount();
    Integer save(T entity) throws Exception;
    void update(T entity) throws Exception;
    void delete(int id) throws Exception;
    Map<Integer, Boolean> delete(int[] ids);
}
