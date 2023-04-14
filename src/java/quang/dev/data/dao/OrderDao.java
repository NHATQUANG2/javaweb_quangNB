package quang.dev.data.dao;

import java.util.List;

import quang.dev.data.dao.model.Order;

public interface OrderDao {
	public boolean insert(Order order);

	public boolean update(Order order);

	public boolean delete(int orderId);

	public Order find(int orderId);

	public Order find(String code);

	public List<Order> findAll();

	public List<Order> findByStatus(String status);

	public int countOrderByDay(String date);

}
