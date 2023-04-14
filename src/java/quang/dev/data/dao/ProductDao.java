package quang.dev.data.dao;

import java.util.List;

import quang.dev.data.dao.model.Product;

public interface ProductDao {
	public boolean insert(Product product);

	public boolean update(Product product);

	public boolean delete(int productId);

	public Product find(int productId);

	public List<Product> findALL();

	public List<Product> findByCategory(int categoryId);

	public List<Product> findByCategory(int categoryId, String orderBy, String order);

	public List<Product> findByName(String name);
}
