package DAO.Abstracts;

import DAO.Interfaces.IAbstractDAO;
import Mapper.Interfaces.IRowMapper;
import Utils.MyDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements IAbstractDAO<T> {
    protected MyDBConnection DBConnect;
    protected ResultSet resultSet;

    protected AbstractDAO() {
        this.DBConnect = new MyDBConnection("localhost", "root","", "coffeestore");
    }

    @Override
    public List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        try {
            resultSet = DBConnect.executeQuery(sql, parameters);
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return results;
    }

    @Override
    public boolean update(String sql, Object... parameters) {
        try {
            int affectedRows ;
            affectedRows = DBConnect.executeUpdate(sql, parameters);
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            DBConnect.rollback();
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public Integer insert(String sql, Object... parameters) {
        try {
            Integer id = null;
            resultSet =  DBConnect.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS, parameters);
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            DBConnect.rollback();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public int count(String sql, Object... parameters) {
        int count = 0;
        try {
            resultSet = DBConnect.executeQuery(sql, parameters);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return count;
    }

    private void closeConnection() {
        try {
            DBConnect.Close();
            if (resultSet != null)
                resultSet = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
