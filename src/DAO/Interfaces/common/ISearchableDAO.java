package DAO.Interfaces.common;

import java.util.ArrayList;

public interface ISearchableDAO<T> {
    ArrayList<T> findAll();
    T findByID(int id);
}
