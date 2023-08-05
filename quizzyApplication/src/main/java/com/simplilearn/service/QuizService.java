package com.simplilearn.service;

import java.util.List;

import com.simplilearn.entity.Category;
import com.simplilearn.entity.Quiz;

public interface QuizService {
	
	public Quiz createQuiz(Quiz quiz);
	public List<Quiz> getQuizzes();
	public List<Quiz> getQuizzesByCategory(Category category);
	public List<Quiz> getQuizzesByActiveStatus();
	public List<Quiz> getQuizzesByCategoryAndActiveStatus(Category category);
	public Quiz getQuizById(Long qid);
	public Quiz updateQuiz(Quiz quiz);
	public void deleteQuiz(Long qid);
	

	
}
