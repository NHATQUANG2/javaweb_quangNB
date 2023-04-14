package quang.dev.data.dao;

import java.util.List;

import quang.dev.data.dao.model.OrderDetail;

public interface OrderDetailDao {
	public boolean insert(OrderDetail orderdetail);

	public boolean update(OrderDetail orderdetail);

	public boolean delete(int orderdetailId);

	public OrderDetail find(int orderdetailId);

	public List<OrderDetail> findALL();

	public List<OrderDetail> findByOrder(int orderId);

}
