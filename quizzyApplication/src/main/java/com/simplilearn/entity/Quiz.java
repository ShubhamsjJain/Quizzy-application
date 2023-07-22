package com.simplilearn.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="quiz")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long quiz_id;
	private String title;
	private String description;
	private int maxMarks;
	private int num_Of_Questions;
	private boolean active = false;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Category category;
	
	@OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY,mappedBy="quiz")
	@JsonIgnore
	private Set<Question> questions = new HashSet<>();
	
	public Quiz() {
		
	}
	
	

	public Quiz(Long quiz_id, String title, String description, int maxMarks, int num_Of_Questions, boolean active,
			Category category, Set<Question> questions) {
		super();
		this.quiz_id = quiz_id;
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.num_Of_Questions = num_Of_Questions;
		this.active = active;
		this.category = category;
		this.questions = questions;
	}



	public Long getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(Long quiz_id) {
		this.quiz_id = quiz_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}

	public int getNum_Of_Questions() {
		return num_Of_Questions;
	}

	public void setNum_Of_Questions(int num_Of_Questions) {
		this.num_Of_Questions = num_Of_Questions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Set<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	
	
	
	

}
