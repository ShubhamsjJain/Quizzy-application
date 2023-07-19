package com.simplilearn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.simplilearn.config.JwtUtil;
import com.simplilearn.entity.JwtRequest;
import com.simplilearn.entity.JwtResponse;
import com.simplilearn.service.UserDetailServiceImpl;

@RestController
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailServiceImpl userdetailserviceimpl;
	
	@Autowired
	private JwtUtil jwtutil;
	
	
	//generate token
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtrequest) throws Exception {
		
		try {
			
			authenticate(jwtrequest.getUsername(),jwtrequest.getPassword());
			
		}catch(UsernameNotFoundException e) {
			
			e.printStackTrace();
			throw new Exception("USER NOT FOUND !!");
			
		}
		
		
		//Authenticate
		
		UserDetails userdetails =this.userdetailserviceimpl.loadUserByUsername(jwtrequest.getUsername());
		String token = this.jwtutil.generateToken(userdetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username , String password) throws Exception {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		}catch(DisabledException e) {
			
			throw new Exception("USER DISABLED" + e.getMessage());
			
		}catch(BadCredentialsException e) {
			
			throw new Exception("INVALID CREDENTIALS" + e.getMessage());
		}
	}
	

}
