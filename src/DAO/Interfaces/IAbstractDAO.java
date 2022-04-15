package DAO.Interfaces;

import Mapper.Interfaces.IRowMapper;

import java.io.Serializable;
import java.util.List;

public interface IAbstractDAO<T> extends Serializable {
    List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters);
    boolean update(String sql, Object... parameters);
    Integer insert(String sql, Object... parameters);
    int count(String sql, Object... parameters);
}
