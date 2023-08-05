package com.simplilearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.entity.Category;
import com.simplilearn.entity.Quiz;
import com.simplilearn.repo.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	private QuizRepository quizrepo;

	@Override
	public Quiz createQuiz(Quiz quiz) {
		
		return quizrepo.save(quiz);
	}

	@Override
	public List<Quiz> getQuizzes() {
		
		return quizrepo.findAll();
	}

	@Override
	public Quiz getQuizById(Long qid) {
		
		return quizrepo.findById(qid).get();
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return quizrepo.save(quiz);
	}

	@Override
	public void deleteQuiz(Long qid) {
		
		quizrepo.deleteById(qid);
		
	}

	@Override
	public List<Quiz> getQuizzesByCategory(Category category) {
		
		List<Quiz> quizzes = quizrepo.findByCategory(category);
		
		return quizzes;
	}

	//Get Active Quizzes
	
	@Override
	public List<Quiz> getQuizzesByActiveStatus() {
		
		List<Quiz> quizzes = quizrepo.findByActive(true);
		return quizzes;
	}

	@Override
	public List<Quiz> getQuizzesByCategoryAndActiveStatus(Category category) {
		
		List<Quiz> quizzes = quizrepo.findByCategoryAndActive(category, true);
		return quizzes;
		
	}


}
