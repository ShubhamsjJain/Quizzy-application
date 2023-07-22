package com.simplilearn.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.entity.Role;
import com.simplilearn.entity.User;
import com.simplilearn.entity.UserRole;
import com.simplilearn.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	private UserService userservice;
	private BCryptPasswordEncoder passwordencoder;
	
	@Autowired
	public UserController(UserService userservice, BCryptPasswordEncoder passwordencoder) {
		super();
		this.userservice = userservice;
		this.passwordencoder = passwordencoder;
	}

	
	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody User user) throws Exception{
		
		
		//User
		//Encoding password with bcrypt password encoder
		user.setPassword(this.passwordencoder.encode(user.getPassword()));
		
		Set<UserRole> userroles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleId(44L);
		role.setRoleName("USER");
		
		UserRole userrole = new UserRole();
		userrole.setUser(user);
		userrole.setRoles(role);
		
		userroles.add(userrole);
		
		User ur = userservice.createUser(user, userroles);
		
		if(ur.getUsername() != null) {
			return ResponseEntity.ok(ur);
		}else {
			return ResponseEntity.ok("Username already exists");
		}
		
        
		
		
		
	}
	
	

	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String username) {
		return userservice.getUserByUserName(username);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		
		userservice.deleteUserById(id);
		
	}
	

}
