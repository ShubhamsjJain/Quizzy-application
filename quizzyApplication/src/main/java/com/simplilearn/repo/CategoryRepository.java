package com.simplilearn.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
	

}
