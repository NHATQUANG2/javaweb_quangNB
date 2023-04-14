package quang.dev.data.dao;

import java.util.List;

import quang.dev.data.dao.model.Category;

public interface CategoryDao {
	public boolean insert(Category Category);
	
	public boolean update(Category Category);
	
	public boolean delete(int id);
	
	public Category find(int id);
	
	public List<Category> findAll();
}
