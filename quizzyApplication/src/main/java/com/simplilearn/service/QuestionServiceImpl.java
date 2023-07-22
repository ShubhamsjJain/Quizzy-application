package com.simplilearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.entity.Question;
import com.simplilearn.entity.Quiz;
import com.simplilearn.repo.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionrepo;
	
	@Override
	public Question createQuestion(Question question) {
		
		return questionrepo.save(question);
	}

	@Override
	public List<Question> getQuestions() {
		
		return questionrepo.findAll();
	}

	@Override
	public Question getQuestionById(Long question_id) {
		
		return questionrepo.findById(question_id).get();
	}

	@Override
	public Question updateQuestion(Question question) {
		
		return questionrepo.save(question);
	}

	@Override
	public void deleteQuestion(Long questionid) {
		
		questionrepo.deleteById(questionid);
		
	}

	@Override
	public List<Question> getAllQuesOfQuiz(Quiz quiz) {
		
		
		return questionrepo.findByQuiz(quiz);
	}

}
