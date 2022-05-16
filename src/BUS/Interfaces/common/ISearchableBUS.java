package BUS.Interfaces.common;

import java.util.ArrayList;

public interface ISearchableBUS<T> {
    ArrayList<T> findAll();
    T findByID(int id);
    boolean isExist(T entity);
    int getTotalCount();
}
