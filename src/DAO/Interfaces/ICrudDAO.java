package DAO.Interfaces;

import java.io.Serializable;
import java.util.List;

public interface ICrudDAO<T> extends Serializable {
    void Insert(T Entity);
    int Update(T Entity);
    boolean Delete(int id);

    int Count();
    boolean isExist(T Entity);

    List<T> findAll();
    T findByID(int id);
    T findByName(String name);
}
