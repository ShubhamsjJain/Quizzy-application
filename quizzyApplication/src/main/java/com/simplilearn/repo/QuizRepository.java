package com.simplilearn.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.entity.Category;
import com.simplilearn.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
	
	public List<Quiz> findByCategory(Category category);
	public List<Quiz> findByActive(Boolean b);
	public List<Quiz> findByCategoryAndActive(Category category, Boolean b);

}
