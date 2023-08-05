package com.simplilearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
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
import com.simplilearn.entity.Quiz;
import com.simplilearn.service.CategoryService;
import com.simplilearn.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired
	private QuizService quizservice;
	
	@Autowired
	private CategoryService catService;
	
	//Add Quiz
	
	@PostMapping("/")
	public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz){
		
		return ResponseEntity.ok(quizservice.createQuiz(quiz));
	
	}
	
	//List all quizzes
	
	@GetMapping("/")
	public ResponseEntity<List<Quiz>> getQuizzes(){
		
		return ResponseEntity.ok(quizservice.getQuizzes());
	}
	
	//Get Quiz by quiz id
	
	@GetMapping("/{quiz_id}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("quiz_id") Long quiz_id){
		
		return ResponseEntity.ok(quizservice.getQuizById(quiz_id));
	}
	
	//Get quizzes by category
	
	@GetMapping("/category/{cid}")
	public ResponseEntity<List<Quiz>> quizzesByCategoryId(@PathVariable("cid") Long cat_id){
		
		Category cat = catService.getCategoryById(cat_id);
		
		return ResponseEntity.ok(quizservice.getQuizzesByCategory(cat));
	
	}
	
	//Get Active quizzes
	
	@GetMapping("/active")
	public ResponseEntity<List<Quiz>> getAllActiveQuizzes(){
		
		return ResponseEntity.ok(quizservice.getQuizzesByActiveStatus());
	}
	
	//Get Active quizzes by category
	
	@GetMapping("/active/category/{cid}")
	public ResponseEntity<List<Quiz>> getActiveQuizzesByCategory(@PathVariable("cid") Long cid){
		
		Category category = catService.getCategoryById(cid);
		return ResponseEntity.ok(quizservice.getQuizzesByCategoryAndActiveStatus(category));
	}
	
	//Update quiz
	
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
		
		return ResponseEntity.ok(quizservice.updateQuiz(quiz));
	}
	
	//Delete quiz
	
	@DeleteMapping("/{quiz_id}")
	public void deleteQuiz(@PathVariable("quiz_id") Long quiz_id) {
		
		quizservice.deleteQuiz(quiz_id);
		
	}

}
