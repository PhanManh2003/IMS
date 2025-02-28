package dao;

import java.sql.*;

public class DBContext {

    protected Connection connection;

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-MLOTP07\\MANH:1433;databaseName=FORUSER";
            String user = "sa";
            String password = "123";
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error " + e.getMessage() + " at DBContext");
            return null;
        }

    }
    
    public static void main(String[] args) {
        DBContext test = new DBContext();
        test.connection = test.getConnection();
        System.out.println(test.connection);
    }
}
