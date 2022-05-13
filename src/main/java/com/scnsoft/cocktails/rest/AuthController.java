package com.scnsoft.cocktails.rest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scnsoft.cocktails.JwtTokenUtil;
import com.scnsoft.cocktails.dao.UserRepository;
import com.scnsoft.cocktails.entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping
	public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		request.login(login, password);
		Authentication auth = (Authentication) request.getUserPrincipal();
		User user = (User) auth.getPrincipal();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(user));
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
//		HttpSession session = request.getSession();
//		session.setAttribute("userId" , user.getId());
//		session.setAttribute("userRole" , user.getRole());
	}

	@PostMapping("/logout")
	public void logout(HttpServletRequest request) throws ServletException {
		request.logout();
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(BadCredentialsException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.UNAUTHORIZED);
	}
}
