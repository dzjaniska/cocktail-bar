package com.scnsoft.cocktails.rest;

import java.util.NoSuchElementException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scnsoft.cocktails.dto.CreateSetDTO;
import com.scnsoft.cocktails.dto.SetDTO;
import com.scnsoft.cocktails.dto.SetSearch;
import com.scnsoft.cocktails.facade.SetFacade;
import com.scnsoft.cocktails.service.AuthorizationException;
import com.scnsoft.cocktails.service.SetStatusException;
import com.scnsoft.cocktails.service.UserRoleException;

@RestController
@RequestMapping("/api/sets")
public class SetRestController {

	@Autowired
	private SetFacade setFacade;
	
	@GetMapping
	public Page<SetDTO> getSets(HttpSession session, SetSearch search, Pageable pageable) {
		return setFacade.findAll(session, search, pageable);
	}
	
	@GetMapping("/{setId}")
	public SetDTO getSet(HttpSession session, @PathVariable UUID setId) {
		return setFacade.findById(session, setId);
	}
	
	@PostMapping
	public SetDTO addSet(HttpSession session, @RequestBody CreateSetDTO theSet) {
		return setFacade.save(session, theSet);
	}
	
	@PostMapping("/{setId}/join")
	public SetDTO joinSet(HttpSession session, @PathVariable UUID setId, @RequestBody String password) {
		return setFacade.join(session, setId, password);
	}
	
	@PutMapping("/{setId}")
	public SetDTO updateSet(HttpSession session, @PathVariable UUID setId, @RequestBody SetDTO theSet) {
		return setFacade.update(session, setId, theSet);
	}
	
	@DeleteMapping("/{setId}")
	public ResponseEntity<String> deleteSet(HttpSession session, @PathVariable UUID setId) {
		
		setFacade.delete(session, setId);
		
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(NoSuchElementException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(EmptyResultDataAccessException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(UserRoleException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(SetStatusException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(AuthorizationException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
