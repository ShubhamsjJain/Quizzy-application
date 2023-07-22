package com.simplilearn.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

}
