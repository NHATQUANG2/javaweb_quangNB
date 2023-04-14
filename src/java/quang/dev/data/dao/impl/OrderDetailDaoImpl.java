package quang.dev.data.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quang.dev.data.dao.OrderDetailDao;
import quang.dev.data.dao.model.OrderDetail;
import quang.dev.data.driver.MySQLDriver;

public class OrderDetailDaoImpl implements OrderDetailDao {

	@Override
	public boolean insert(OrderDetail orderdetail) {
		// TODO Auto-generated method stub
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "INSERT INTO ORDER_DETAILS(id, product_id, order_id, quantity, price) VALUES(NULL,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderdetail.getProductId());
			stmt.setInt(2, orderdetail.getOrderId());
			stmt.setInt(3, orderdetail.getQuantity());
			stmt.setDouble(4, orderdetail.getPrice());

			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean update(OrderDetail orderdetail) {
		// TODO Auto-generated method stub
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "UPDATE ORDER_DETAILS SET PRODUCT_ID=?, ORDER_ID =?,QUANTITY =?, PRICE=? WHERE ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderdetail.getProductId());
			stmt.setInt(2, orderdetail.getOrderId());
			stmt.setInt(3, orderdetail.getQuantity());
			stmt.setDouble(4, orderdetail.getPrice());
			stmt.setInt(5, orderdetail.getId());
			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean delete(int orderdetailId) {
		// TODO Auto-generated method stub
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			String sql = "DELETE FROM ORDER_DETAILS WHERE ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, orderdetailId);
			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public OrderDetail find(int orderdetailId) {
		// TODO Auto-generated method stub
		   Connection conn = MySQLDriver.getInstance().getConnection();
		 try {
	            String sql = "SELECT * FROM ORDER_DETAILS WHERE ID=?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, orderdetailId);
	            ResultSet rs = (ResultSet) stmt.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                int quantity = rs.getInt("quantity");
	                int product_id = rs.getInt("product_id");
	                int order_id = rs.getInt("order_id");
	                double price =rs.getDouble("price");
	                return new OrderDetail(id, product_id, order_id, quantity, price);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
		return null;
	}

	@Override
	public List<OrderDetail> findALL() {
		// TODO Auto-generated method stub
		List<OrderDetail> orderdetailList = new ArrayList<OrderDetail>();
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "SELECT * FROM ORDER_DETAILS";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	int id = rs.getInt("id");
                int product_id = rs.getInt("product_id");
                int order_id = rs.getInt("order_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                orderdetailList.add(new OrderDetail(id, product_id, order_id, quantity, price));
            }
        } catch (SQLException ex) {}
		
		return orderdetailList;  
	}

	@Override
	public List<OrderDetail> findByOrder(int orderId) {
		// TODO Auto-generated method stub
		List<OrderDetail> orderdetail = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM ORDER_DETAILS WHERE ORDER_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                int productId = rs.getInt("product_id");
                double price = rs.getDouble("price");
                orderdetail.add(new OrderDetail(id, productId, orderId, quantity, price));
            }
        } catch (SQLException ex) {
        }

        return orderdetail;
	}

}
