package com.simplilearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simplilearn.entity.User;
import com.simplilearn.repo.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	private UserRepository userrepo;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userrepo.findByUsername(username);
		
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("No User Found !!");
		}
		return user;
	}

}
