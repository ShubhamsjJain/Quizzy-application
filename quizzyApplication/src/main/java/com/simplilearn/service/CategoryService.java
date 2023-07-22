package com.simplilearn.service;

import java.util.List;

import com.simplilearn.entity.Category;

public interface CategoryService {
	
	public Category createCategory(Category category);
	public List<Category> getCategories();
	public Category getCategoryById(Long cid);
	public Category updateCategory(Category category);
	public void deleteCategory(Long cid);
	

}
