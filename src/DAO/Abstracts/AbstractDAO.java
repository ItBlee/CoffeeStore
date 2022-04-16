package DAO.Abstracts;

import DAO.Interfaces.IAbstractDAO;
import Mapper.Interfaces.IRowMapper;
import Utils.MyDBConnection;

import java.sql.*;
import java.util.ArrayList;

import static Utils.SystemConstant.*;

public abstract class AbstractDAO<T> implements IAbstractDAO<T> {
    protected MyDBConnection DBConnect;

    protected AbstractDAO() {
        this.DBConnect = new MyDBConnection(DB_HOST, DB_NAME, DB_USERNAME, DB_PASSWORD);
    }

    @Override
    public ArrayList<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters) {
        ArrayList<T> results = new ArrayList<T>();
        try {
            ResultSet resultSet = DBConnect.executeQuery(sql, parameters);
            while (resultSet.next())
                results.add(rowMapper.mapRow(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
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
            closeDBConnection();
        }
        return false;
    }

    @Override
    public Integer insert(String sql, Object... parameters) {
        Integer id = null;
        try {
            ResultSet resultSet =  DBConnect.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS, parameters);
            if (resultSet.next())
                id = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            DBConnect.rollback();
        } finally {
            closeDBConnection();
        }
        return id;
    }

    @Override
    public int count(String sql, Object... parameters) {
        int count = 0;
        try {
            ResultSet resultSet = DBConnect.executeQuery(sql, parameters);
            while (resultSet.next())
                count = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return count;
    }

    private void closeDBConnection() {
        try {
            DBConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
