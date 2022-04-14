package com.scnsoft.cocktails.rest;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scnsoft.cocktails.dto.UserDTO;
import com.scnsoft.cocktails.facade.UserFacade;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserFacade userFacade;
	
	@GetMapping
	public Page<UserDTO> getUsers(Pageable pageable) {
		return userFacade.findAll(pageable);
	}

	@GetMapping("/current")
	public UserDTO getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		Object userId = session.getAttribute("userId");
		if (userId == null) {
			return null;
		}

		return userFacade.findById(UUID.fromString(userId.toString()));
	}

	@GetMapping("/{userId}")
	public UserDTO getUser(@PathVariable UUID userId) {
		return userFacade.findById(userId);
	}
	
	@PostMapping
	public UserDTO addUser(@RequestBody UserDTO theUser) {
		return userFacade.save(theUser);
	}
	
	@PutMapping("/{userId}")
	public UserDTO updateUser(HttpSession session, @PathVariable UUID userId, @RequestBody UserDTO theUser) {
		return userFacade.update(session, userId, theUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
		userFacade.deleteById(userId);
		
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(AccessDeniedException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(EntityNotFoundException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(EmptyResultDataAccessException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
