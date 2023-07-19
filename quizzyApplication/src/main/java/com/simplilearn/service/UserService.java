package com.simplilearn.service;

import java.util.Set;

import com.simplilearn.entity.User;
import com.simplilearn.entity.UserRole;

public interface UserService {
	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception; //i.e if there is a need to give a user 2 or more roles

	public User getUserByUserName(String username);
	
	public void deleteUserById(Long id);
}
