package com.simplilearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.simplilearn.service.UserService;
import com.simplilearn.entity.User;
import com.simplilearn.entity.Role;
import com.simplilearn.entity.UserRole;
import java.util.*;

@SpringBootApplication
public class QuizzyApplication implements CommandLineRunner {

	
	
	private UserService userService;
	
	private BCryptPasswordEncoder passwordencoder;
	
	
	@Autowired
	public QuizzyApplication(UserService userService) {
		super();
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(QuizzyApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
//		this.passwordencoder=new BCryptPasswordEncoder();
//		
//		System.out.println("Starting code");
//		
//        User user = new User();
//		
//		user.setFirstname("Jeff");
//		user.setLastname("Bezoz");
//		user.setUsername("admin1234");
//		user.setPassword(this.passwordencoder.encode("admin123"));
//		user.setEmail("admin@gmail.com");
//		
//		Role role = new Role();
//		
//		role.setRoleId(45L);
//		role.setRoleName("ADMIN");
//		
//		UserRole userrole = new UserRole();
//		
//		userrole.setUser(user);
//		userrole.setRoles(role);
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		
//		userRoleSet.add(userrole);
//		
//		User admin = this.userService.createUser(user, userRoleSet);
//		
//		System.out.println(admin.getUsername());
		
	}

	

}
