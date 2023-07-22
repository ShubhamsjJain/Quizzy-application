package com.simplilearn.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="categories")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cid;
	private String title;
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="category")
	@JsonIgnore
	private Set<Quiz> quizzes = new LinkedHashSet<>(); // Linked List + Hashset - to maintain insertion order of quizzes
	
	public Category() {
		
	}
	
	

	public Category(Long cid, String title, String description, Set<Quiz> quizzes) {
		super();
		this.cid = cid;
		this.title = title;
		this.description = description;
		this.quizzes = quizzes;
	}



	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
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



	public Set<Quiz> getQuizzes() {
		return quizzes;
	}



	public void setQuizzes(Set<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	
	
	
	
	

}
