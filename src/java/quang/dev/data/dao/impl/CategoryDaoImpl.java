package quang.dev.data.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quang.dev.data.dao.CategoryDao;
import quang.dev.data.dao.model.Category;
import quang.dev.data.driver.MySQLDriver;

public class CategoryDaoImpl implements CategoryDao {
private Connection conn;
	
	public CategoryDaoImpl() {
		this.conn = MySQLDriver.getInstance().getConnection();
	}

	@Override
	public boolean insert(Category category) {
		// TODO Auto-generated method stub
		Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "INSERT INTO CATEGORIES(ID, NAME, IMAGE) VALUES(NULL, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, category.getName());
			stmt.setString(2, category.getImage());

            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }
		
		return false;
	}

	@Override
	public boolean update(Category category) {
		// TODO Auto-generated method stub
		try {
            String sql = "UPDATE CATEGORIES SET NAME=?, IMG=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getImage());
            

            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }

		return false;
	}

	@Override
	public boolean delete(int categoryId) {
		// TODO Auto-generated method stub
		try {
            Connection conn = MySQLDriver.getInstance().getConnection();
            String sql = "DELETE FROM CATEGORIES WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, categoryId);
            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }
		return false;
	}

	@Override
	public Category find(int categoryId) {
		// TODO Auto-generated method stub
		 try {
	            String sql = "SELECT * FROM CATEGORIES WHERE ID=?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, categoryId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String img = rs.getString("img");
	                return new Category(id, name, img);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
		return null;
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		 List<Category> categoryList = new ArrayList<Category>();
	        Connection conn = MySQLDriver.getInstance().getConnection();
	        try {
	            String sql = "SELECT * FROM CATEGORIES";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String img = rs.getString("img");

	                categoryList.add(new Category(id, name, img));
	            }
	        } catch (SQLException ex) {
	        }

	        return categoryList;
	}

}
