package dao.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.test.Account;
import dao.DBContext;

public class AccountDAO extends DBContext {

    public List<Account> findAll() {
        List<Account> list = new ArrayList<>();
        // READ:
        //    connect with DB
        connection = getConnection();
        //    Chuẩn bị câu lệnh sql
        String sql = "SELECT * FROM dbo.Account";
        try {
            //    Tạo đối tượng preparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            //    setParameter (optional)
            //    executeQuery
            ResultSet rs = statement.executeQuery();
            //    Trả về kết quả
            while (rs.next()) {
                String username = rs.getString("username").trim();
                String password = rs.getString("password").trim();
                Account account = new Account(username, password);
                list.add(account);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Account findByLogin(String username, String password) {
        //    connect with DB
        connection = getConnection();
        //    Chuẩn bị câu lệnh sql
        String sql = "SELECT * FROM dbo.Account WHERE [username]"
                + " = ? AND [password] = ?";
        try {
            //    Tạo đối tượng preparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            //    setParameter (optional)
            statement.setObject(1, username);
            statement.setObject(2, password);
            //    executeQuery
            ResultSet rs = statement.executeQuery();
            //    Trả về kết quả
            if (rs.next()) {
                String usernameFound = rs.getString("username").trim();
                String passwordFound = rs.getString("password").trim();
                Account account = new Account(username, password);
                return account;
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        System.out.println(accountDAO.findByLogin("4user", "2"));
    }
}
