package com.simplilearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.entity.Category;
import com.simplilearn.service.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryservice;
	
	
	//Add Category
	
	@PostMapping("/")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		
		Category cat = categoryservice.createCategory(category);
		return ResponseEntity.ok(cat);
		
	}
	
	//Get Category
	
	@GetMapping("/{cid}")
	public ResponseEntity<Category> getCategory(@PathVariable("cid") Long cid) {
		
		Category cat = categoryservice.getCategoryById(cid);
		return ResponseEntity.ok(cat);
		
	}
	
	//Get all categories
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getCategories(){
		
		return ResponseEntity.ok(categoryservice.getCategories());
	}
	
	//Update category
	
	@PutMapping("/")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		
		return ResponseEntity.ok(categoryservice.updateCategory(category));
	}

	//Delete category
	
	@DeleteMapping("/{cid}")
	public void deleteCategory(@PathVariable("cid") Long cid) {
		
		categoryservice.deleteCategory(cid);
		
	}
}
