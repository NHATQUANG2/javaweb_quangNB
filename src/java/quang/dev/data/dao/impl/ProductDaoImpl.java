package quang.dev.data.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import quang.dev.data.dao.ProductDao;
import quang.dev.data.dao.model.Product;
import quang.dev.data.driver.MySQLDriver;

public class ProductDaoImpl implements ProductDao {

    private final Connection conn;

    public ProductDaoImpl() {
        this.conn = MySQLDriver.getInstance().getConnection();
    }
    @Override
    public boolean insert(Product product) {
        // TODO Auto-generated method stub
       
        try {
            String sql = "INSERT INTO PRODUCTS(id, name, description, price, quantity, view, created_at, category_id) VALUES(NULL, ?, ?, ?, ?, ?, ? ,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getView());
            stmt.setTimestamp(6, product.getCreatedAt());
            stmt.setInt(7, product.getCategoryId());
            stmt.execute();
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        // TODO Auto-generated method stub
       
        try {
            String sql = "UPDATE PRODUCTS SET NAME=?, DESCRIPTION=?, PRICE=?, QUANTITY=?, VIEW=?, CATEGORY_ID=?, CREATED_AT=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getView());
            stmt.setInt(6, product.getCategoryId());
            stmt.setTimestamp(7, product.getCreatedAt());
            stmt.setInt(8, product.getId());
            return stmt.execute();

        } catch (Exception e) {
            // TODO: handle exception\
        }

        return false;
    }

     @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        try {
            String sql = "DELETE FROM PRODUCTS WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.execute();

        } catch (SQLException e) {
            // TODO: handle exception
        }
        return false;
    }

    @Override
    public Product find(int productId) {
        // TODO Auto-generated method stub
       
        try {
            String sql = "SELECT * FROM PRODUCTS WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int view = rs.getInt("view");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                Timestamp createdAt = rs.getTimestamp("created_at");

                return new Product(id, name, description, price, quantity, view, categoryId, createdAt);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @Override
    public List<Product> findALL() {
        // TODO Auto-generated method stub
        List<Product> productList = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM PRODUCTS";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int view = rs.getInt("view");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                Timestamp createdAt = rs.getTimestamp("created_at");

                productList.add(new Product(id, name, description, price, quantity, view, categoryId, createdAt));
            }
        } catch (SQLException ex) {
        }

        return productList;

    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        // TODO Auto-generated method stub
        List<Product> productList = new ArrayList<>();
       
        try {
            String sql = "SELECT * FROM PRODUCTS WHERE CATEGORY_ID=?  ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoryId);
//	            stmt.setString(2, orderBy);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int view = rs.getInt("view");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                categoryId = rs.getInt("category_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                productList.add(new Product(id, name, description, price, quantity, view, categoryId, createdAt));
            }
        } catch (SQLException ex) {
        }

        return productList;

    }

    @Override
    public List<Product> findByCategory(int categoryId, String orderBy, String order) {
        // TODO Auto-generated method stub
        List<Product> productList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM PRODUCTS WHERE CATEGORY_ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoryId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int view = rs.getInt("view");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                categoryId = rs.getInt("category_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                productList.add(new Product(id, name, description, price, quantity, view, categoryId, createdAt));
            }
        } catch (SQLException ex) {
        }

        return productList;
    }

    @Override
    public List<Product> findByName(String name) {
        // TODO Auto-generated method stub
        List<Product> productList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM PRODUCTS WHERE NAME LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                name = rs.getString("name");
                String description = rs.getString("description");
                int view = rs.getInt("view");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                productList.add(new Product(id, name, description, price, quantity, view, categoryId, createdAt));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return productList;
    }

}
