package DAO.Abstracts;

import DAO.Interfaces.IDAO;
import Mapper.Interfaces.IRowMapper;
import Utils.MyDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements IDAO<T> {
    protected MyDBConnection DBConnect;
    protected ResultSet resultSet;

    protected AbstractDAO() {
        this.DBConnect = new MyDBConnection("localhost", "root", "", "coffeestore");
    }

    @Override
    public List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        try {
            resultSet = DBConnect.executeQuery(sql, parameters);
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public boolean update(String sql, Object... parameters) {
        try {
            int affectedRows ;
            DBConnect.setAutoCommit(false);
            affectedRows = DBConnect.executeUpdate(sql, parameters);
            DBConnect.commit();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (DBConnect.isClosed())
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
            DBConnect.setAutoCommit(false);
            resultSet =  DBConnect.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS, parameters);
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            DBConnect.commit();
            return id;
        } catch (Exception e) {
            if (DBConnect.isClosed())
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
