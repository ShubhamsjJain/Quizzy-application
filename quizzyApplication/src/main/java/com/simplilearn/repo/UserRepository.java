package com.simplilearn.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

}
