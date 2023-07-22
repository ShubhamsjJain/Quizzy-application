package com.simplilearn.service;

import java.util.List;

import com.simplilearn.entity.Question;
import com.simplilearn.entity.Quiz;



public interface QuestionService {
	
	public Question createQuestion(Question question);
	public List<Question> getQuestions();
	public Question getQuestionById(Long question_id);
	public Question updateQuestion(Question question);
	public void deleteQuestion(Long questionid);
	
	public List<Question> getAllQuesOfQuiz(Quiz quiz);

}
