package Utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class MyDBConnection {
    String Host;
    String UserName;
    String Database;
    String Password;
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public MyDBConnection(String Host, String UserName, String Password, String Database) {
        this.Host = Host;
        this.UserName = UserName;
        this.Password = Password;
        this.Database = Database;
    }     
     
    public void driverTest() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("MySQL Driver JDBC not found");
        }
    }

    public Connection getConnection() throws Exception {
        if (this.connection == null) {
            driverTest();
            String url = "jdbc:mysql://" + this.Host + ":3306/" + this.Database
                            + "?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
            try {
                this.connection = DriverManager.getConnection(url, this.UserName, this.Password);
            } catch (SQLException e){
                throw new Exception("Unable to connect MySQL server ! " + e);
            }
        }
        return this.connection;
    }

    public Statement getStatement() throws Exception {
        if (this.statement == null || this.statement.isClosed())
            this.statement = this.getConnection().createStatement();

        return this.statement;
    }

    public ResultSet executeQuery(String query) throws Exception {
        try {           
            this.resultSet = getStatement().executeQuery(query);
        }catch(Exception e) {
            
            throw new Exception("Error: " + e.getMessage());
        }
           
        return this.resultSet;
    }

    public int executeUpdate(String query) throws Exception {
        int result;
        
        try {
            result = getStatement().executeUpdate(query);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        } finally {			
            this.Close();
        }
        
        return result;
    }
	
    public void Close() throws Exception {
        if (this.resultSet != null && !this.resultSet.isClosed()) {
            this.resultSet.isClosed();
            this.resultSet = null;
        }
        
        if (this.statement != null && !this.statement.isClosed()) {
            this.statement.isClosed();
            this.statement = null;
        }
        
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.isClosed();
            this.connection = null;
        }       
    }    
}