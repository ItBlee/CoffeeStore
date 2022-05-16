package DAO.Interfaces.common;

public interface ICrudDAO<T> {
    Integer save(T entity);
    boolean update(T entity);
    boolean delete(int id);
}
