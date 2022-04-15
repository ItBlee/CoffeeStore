package DAO.Interfaces;

public interface IDAO<T> {
    Integer save(T entity);
    boolean update(T entity);
    boolean delete(int id);
    int count();
}
