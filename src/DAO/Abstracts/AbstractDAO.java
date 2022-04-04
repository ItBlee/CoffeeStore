package DAO.Abstracts;

import Utils.MyDBConnection;

import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class AbstractDAO<T> {
    MyDBConnection connect;
    ResultSet resultSet = null;
    ArrayList<T> entityList;

    public AbstractDAO() {
        connect = new MyDBConnection("localhost", "root", "", "coffeestore");
    }
}
