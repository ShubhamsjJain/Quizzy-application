package com.simplilearn.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.entity.Question;
import com.simplilearn.entity.Quiz;

public interface QuestionRepository extends JpaRepository<Question,Long> {
	
	public List<Question> findByQuiz(Quiz quiz);

}
