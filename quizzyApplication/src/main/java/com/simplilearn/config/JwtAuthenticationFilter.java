package com.simplilearn.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.simplilearn.service.UserDetailServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailServiceImpl userdetailservice;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("requestTokenHeader");
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			//Yes
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				
				username = this.jwtutil.extractUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				
				e.printStackTrace();
				System.out.println("Jwt token has expired");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
			
			
		}else {
			
			System.out.println("Invalid token, not start with bearer string");
		}
		
		//validated
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			final UserDetails userdetails = this.userdetailservice.loadUserByUsername(username);
			if(this.jwtutil.validateToken(jwtToken, userdetails)) {
				
				//Token is valid
				
				UsernamePasswordAuthenticationToken usernamepasswordauthyenticationtoken =new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
				usernamepasswordauthyenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamepasswordauthyenticationtoken);
			}
		}else {
			System.out.println("Token is not valid");
		}
		
		filterChain.doFilter(request, response);
	}

}
