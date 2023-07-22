package com.simplilearn.controllers;

import java.util.ArrayList;
import java.util.Collections;
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

import com.simplilearn.entity.Question;
import com.simplilearn.entity.Quiz;
import com.simplilearn.service.QuestionService;
import com.simplilearn.service.QuizService;


@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private QuestionService questionservice;
	
	@Autowired
	private QuizService quizservice;
	
	//Add question
	
	@PostMapping("/")
	public ResponseEntity<Question> createQuestion(@RequestBody Question question){
		
		return ResponseEntity.ok(questionservice.createQuestion(question));
	}
	
	//List all questions
	
	@GetMapping("/")
	public ResponseEntity<List<Question>> getQuestions(){
		
		return ResponseEntity.ok(questionservice.getQuestions());
	}
	
	//Get question
	
	@GetMapping("/{qid}")
	public ResponseEntity<Question> getQuestion(@PathVariable("qid") Long qid){
		
		return ResponseEntity.ok(questionservice.getQuestionById(qid));
	}
	
	//Update Question
	
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		
		return ResponseEntity.ok(questionservice.updateQuestion(question));
	}

	
	//Delete Question
	
	@DeleteMapping("/{qid}")
	public void deleteQuestion(@PathVariable("qid") Long qid) {
		
		questionservice.deleteQuestion(qid);
		
	}
	
	//Get questions by quiz
	
	@GetMapping("/quiz/{quiz_id}")
	public ResponseEntity<List<Question>> quesByQuiz(@PathVariable("quiz_id")Long quiz_id){
		
		Quiz quiz = quizservice.getQuizById(quiz_id);
		List<Question> questions = questionservice.getAllQuesOfQuiz(quiz);
		
		//we got all questions but quiz may have much more questions than required in a quiz having certain maiximum number
		//of questions,hence we will send only that number of questions as req. by quiz
		
		if(questions.size()>quiz.getNum_Of_Questions()) {
			
			questions=questions.subList(0, quiz.getNum_Of_Questions());
		}
		Collections.shuffle(questions);  // To randomly allocate questions
		return ResponseEntity.ok(questions);
	}
}
