package DAO.Interfaces;

import Mapper.Interfaces.IRowMapper;

import java.io.Serializable;
import java.util.ArrayList;

public interface IAbstractDAO<T> extends Serializable {
    ArrayList<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters);
    boolean update(String sql, Object... parameters);
    Integer insert(String sql, Object... parameters);
    int count(String sql, Object... parameters);
}
