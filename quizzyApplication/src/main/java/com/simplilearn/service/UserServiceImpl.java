package com.simplilearn.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.entity.User;
import com.simplilearn.entity.UserRole;
import com.simplilearn.repo.RoleRepository;
import com.simplilearn.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userrepo;
	
	private RoleRepository rolerepo;
	
	@Autowired
	public UserServiceImpl(UserRepository userrepo, RoleRepository rolerepo) {
		super();
		this.userrepo = userrepo;
		this.rolerepo = rolerepo;
	}

	//Creating User
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User findUser = userrepo.findByUsername(user.getUsername()); //To check if user already exists or not
		
		if(findUser != null) {
			
			System.out.println("User already exists!!!!");
			throw new Exception("User is already there!");
			
		}else {
			
			//Create new User
			
			       //First save all userroles of new user
			
			for(UserRole ur : userRoles) {
				
				rolerepo.save(ur.getRoles());
			}
			
			user.getUserRoles().addAll(userRoles);
			
		}
		return userrepo.save(user);
	}

	
	//Getting user by username
	@Override
	public User getUserByUserName(String username) {
		
		User ur = userrepo.findByUsername(username);
		
		return ur;
	}

	
	//Deleting user
	@Override
	public void deleteUserById(Long id) {
		
		userrepo.deleteById(id);
		
	}

	

	

}
