package com.scnsoft.cocktails.rest;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scnsoft.cocktails.dto.CocktailDTO;
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

	@GetMapping("/{userId}")
	public UserDTO getUser(@PathVariable UUID userId) {
		return userFacade.findById(userId);
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