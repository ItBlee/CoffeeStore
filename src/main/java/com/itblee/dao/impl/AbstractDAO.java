package com.itblee.dao.impl;

import com.itblee.mapper.RowMapper;
import com.itblee.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class AbstractDAO<T> {

    public ArrayList<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        ArrayList<T> results = new ArrayList<>();
        try {
            ResultSet resultSet = ConnectionUtil.executeQuery(sql, parameters);
            while (resultSet.next())
                results.add(rowMapper.mapRow(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return results;
    }

    public boolean update(String sql, Object... parameters) {
        try {
            int affectedRows ;
            affectedRows = ConnectionUtil.executeUpdate(sql, parameters);
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            ConnectionUtil.rollback();
        } finally {
            closeDBConnection();
        }
        return false;
    }

    public Integer insert(String sql, Object... parameters) {
        Integer id = null;
        try {
            ResultSet resultSet = ConnectionUtil.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS, parameters);
            if (resultSet.next())
                id = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            ConnectionUtil.rollback();
        } finally {
            closeDBConnection();
        }
        return id;
    }

    public int count(String sql, Object... parameters) {
        int count = 0;
        try {
            ResultSet resultSet = ConnectionUtil.executeQuery(sql, parameters);
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
            ConnectionUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
