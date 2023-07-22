package com.simplilearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.entity.Category;
import com.simplilearn.repo.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService  {
	
	@Autowired
	private CategoryRepository categoryrepo;
	

	@Override
	public Category createCategory(Category category) {
		
		return categoryrepo.save(category);
	}

	@Override
	public List<Category> getCategories() {
		
		return categoryrepo.findAll();
	}

	@Override
	public Category getCategoryById(Long cid) {
		
		
		return categoryrepo.findById(cid).get();
	}

	@Override
	public Category updateCategory(Category category) {
		
		
		return categoryrepo.save(category);
	}

	@Override
	public void deleteCategory(Long cid) {
		
		categoryrepo.deleteById(cid);
		
	}

}
