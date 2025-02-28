package dao.test;

import dao.DBContext;
import java.util.ArrayList;
import java.util.List;
import entity.test.Product;
import java.sql.*;

public class ProductDAO extends DBContext {

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        // Declare resources
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            // Connect with DB
            connection = getConnection();
            // Prepare SQL statement
            String sql = "SELECT * FROM dbo.Product";
            statement = connection.prepareStatement(sql);
            // Execute query
            rs = statement.executeQuery();

            // Process results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name").trim();
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Product product = new Product(id, name, quantity, price);
                list.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list;
    }

    public List<Product> findByName(String keyword) {
        List<Product> listProduct = new ArrayList<>();
        // Declare resources
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            // Connect with DB
            connection = getConnection();
            // Prepare SQL statement
            String sql = "SELECT * FROM dbo.Product WHERE name LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            // Execute query
            rs = statement.executeQuery();

            // Process results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name").trim();
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Product product = new Product(id, name, quantity, price);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return listProduct;
    }

    public int insert(Product product) {
        int generatedId = -1;
        // Declare resources
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            // Connect with DB
            connection = getConnection();
            // Prepare SQL statement
            String sql = "INSERT INTO [dbo].[Product] ([name], [quantity], [price]) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, product.getName());
            statement.setObject(2, product.getQuantity());
            statement.setObject(3, product.getPrice());

            // Execute update
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();

            // Get generated key
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return generatedId;
    }

    public void update(Product product) {
        // Declare resources
        PreparedStatement statement = null;

        try {
            // Connect with DB
            connection = getConnection();
            // Prepare SQL statement
            String sql = "UPDATE [dbo].[Product] SET [name] = ?, [quantity] = ?, [price] = ? WHERE [id] = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, product.getName());
            statement.setObject(2, product.getQuantity());
            statement.setObject(3, product.getPrice());
            statement.setObject(4, product.getId());

            // Execute update
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void delete(Product product) {
        // Declare resources
        PreparedStatement statement = null;

        try {
            // Connect with DB
            connection = getConnection();
            // Prepare SQL statement
            String sql = "DELETE FROM [dbo].[Product] WHERE [id] = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, product.getId());

            // Execute delete
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
