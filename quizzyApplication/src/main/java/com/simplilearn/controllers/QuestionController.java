package com.simplilearn.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	
	//Get question by question id
	
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
	
	//Get questions by quiz id(for users)
	
	@GetMapping("/quiz/{quiz_id}")
	public ResponseEntity<List<Question>> quesByQuiz(@PathVariable("quiz_id")Long quiz_id){
		
		Quiz quiz = quizservice.getQuizById(quiz_id);
		List<Question> questions = questionservice.getAllQuesOfQuiz(quiz);
		
		Collections.shuffle(questions);  // To randomly allocate questions
		
		//we got all questions but quiz may have much more questions than required in a quiz having certain maiximum number
		//of questions,hence we will send only that number of questions as req. by quiz
		
		if(questions.size()>quiz.getNum_Of_Questions()) {
			
			questions=questions.subList(0, quiz.getNum_Of_Questions());
		}
		
		//To ignore answers while sending it to user so that it will not be visible to user
		questions.forEach((q)->{
			
			q.setAnswer("");
		});
		
		return ResponseEntity.ok(questions);
	}
	
	//Get questions by quiz id(for Admin i.e all questions)
	
		@GetMapping("/quiz/all/{quiz_id}")
		public ResponseEntity<List<Question>> quesByQuizAdmin(@PathVariable("quiz_id")Long quiz_id){
			
			Quiz quiz = quizservice.getQuizById(quiz_id);
			List<Question> questions = questionservice.getAllQuesOfQuiz(quiz);
			
			return ResponseEntity.ok(questions);
		}
		
		//Evaluate quiz
		
		@PostMapping("/eval-quiz")
		public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
			
			  int attempted=0;
			  int correctAnswers=0;
			  int wrongAnswers=0;
			  double marksGot=0;
			 
			  
			
			//Getting question one by one using for each loop
			
			for(Question q : questions){
				
				Question question = this.questionservice.getQuestionById(q.getQid());
				
				System.out.println("Given Answer: "+q.getGivenAnswer());
				System.out.println("Correct Answer: "+ question.getAnswer());
				
				if((q.getGivenAnswer() != null) && (q.getGivenAnswer().equals(question.getAnswer()) )) {
					
					         correctAnswers+=1;
					         double marksSingle = q.getQuiz().getMaxMarks()/q.getQuiz().getNum_Of_Questions();
					         marksGot += marksSingle;
					         attempted += 1;
				}else if((q.getGivenAnswer() != null) && (!q.getGivenAnswer().equals(question.getAnswer()))){
					
					         
					         wrongAnswers += 1;
					         double marksSingle = q.getQuiz().getMaxMarks()/q.getQuiz().getNum_Of_Questions();
					         double negativeMarkForEachQuestion = (marksSingle/3);
					         marksGot -= negativeMarkForEachQuestion;
					         attempted += 1;
				}else if(q.getGivenAnswer() == null) {
					
					         correctAnswers = correctAnswers;
					         wrongAnswers = wrongAnswers;
					         marksGot = marksGot;
					         attempted = attempted;
				}
			}
			
			System.out.println("attempted: "+ attempted);
			System.out.println("correctAnswers; "+correctAnswers);
			System.out.println("wrongAnswers: "+wrongAnswers);
			System.out.println("marksGot: "+marksGot);
			
			Map<String, Object> mapOfEvaluatedQuiz =Map.of("attempted",attempted,"correctAnswers",correctAnswers,"wrongAnswers",wrongAnswers, "marksGot",marksGot);
		
			return ResponseEntity.ok(mapOfEvaluatedQuiz);
		}
}
