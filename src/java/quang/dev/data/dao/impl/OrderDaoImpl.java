package quang.dev.data.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import quang.dev.data.dao.OrderDao;
import quang.dev.data.dao.model.Order;
import quang.dev.data.driver.MySQLDriver;

public class OrderDaoImpl implements OrderDao {

	@Override
	public boolean insert(Order order) {
		// TODO Auto-generated method stub
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "INSERT INTO ORDERS(id, code, status, user_id) VALUES(NULL, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.getCode());
			stmt.setString(2, order.getStatus());
			stmt.setInt(3, order.getUserId());

			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean update(Order order) {
		// TODO Auto-generated method stub
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "UPDATE ORDERS SET CODE=?,  STATUS=?, USER_ID =? WHERE ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.getCode());
			stmt.setString(2, order.getStatus());
			stmt.setInt(3, order.getUserId());

			stmt.setInt(4, order.getId());
			stmt.execute();
		} catch (Exception e) {
            // TODO: handle exception

		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		 try {
	            Connection conn = MySQLDriver.getInstance().getConnection();
	            String sql = "DELETE FROM ORDERS WHERE ID=?";
	            PreparedStatement stmt = conn.prepareStatement(sql);

	            stmt.setInt(1, id);
	            stmt.execute();
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
			return false;
	}

	@Override
	public Order find(int id) {
		// TODO Auto-generated method stub
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			
            String sql = "SELECT * FROM ORDERS WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = (ResultSet) stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
                String code = rs.getString("code");
                String status = rs.getString("status");
                int userId = rs.getInt("user_id");
                return new Order(id, code, status, userId);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
		return null;
	}

	@Override
	public Order find(String code) {
		// TODO Auto-generated method stub
		Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM ORDERS WHERE CODE = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                int userId = rs.getInt("user_id");
                return new Order(id, code, status, userId);
            }

        } catch (SQLException e) {
        }
		return null;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		 List<Order> orderList = new ArrayList<Order>();
	        Connection conn = MySQLDriver.getInstance().getConnection();
	        try {
	            String sql = "SELECT * FROM ORDERS";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String code = rs.getString("code");
	                String status = rs.getString("status");
	                int userId = rs.getInt("user_id");
	                orderList.add(new Order(id, code, status, userId));
	            }
	        } catch (SQLException ex) {
	        }

	        return orderList;
	}

	@Override
	public List<Order> findByStatus(String status) {
		// TODO Auto-generated method stub
		 List<Order> orderList = new ArrayList<Order>();
	        Connection conn = MySQLDriver.getInstance().getConnection();
	        try {
	            String sql = "SELECT * FROM ORDERS WHERE STATUS=?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, status);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String code = rs.getString("code");
	                int userId = rs.getInt("user_id");
	                orderList.add(new Order(id, code, status, userId));
	            }
	        } catch (SQLException ex) {
	        }

	        return orderList;
	}

	@Override
	public int countOrderByDay(String date) {
		// TODO Auto-generated method stub
		 int count = 0;
	        Connection conn = MySQLDriver.getInstance().getConnection();
	        String sql = "SELECT COUNT(*) AS count FROM orders where date(created_at)=?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, date);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                count = rs.getInt("count");
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return count;
	}

}
