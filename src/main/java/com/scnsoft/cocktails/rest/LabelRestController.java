package com.scnsoft.cocktails.rest;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import org.springframework.web.server.ResponseStatusException;

import com.scnsoft.cocktails.dto.LabelDTO;
import com.scnsoft.cocktails.facade.LabelFacade;

@RestController
@RequestMapping("/api/labels")
public class LabelRestController {
	
	@Autowired
	private LabelFacade labelFacade;
	
	@GetMapping
	public List<LabelDTO> getLabels() {
		return labelFacade.findAll();
	}
	
	@GetMapping("/{labelId}")
	public LabelDTO getLabel(@PathVariable UUID labelId) {
		return labelFacade.getById(labelId);
	}
	
	@PostMapping
	public LabelDTO addLabel(@RequestBody LabelDTO theLabel) {
		return labelFacade.save(theLabel);
	}
	
	@PutMapping
	public LabelDTO updateLabel(@RequestBody LabelDTO theLabel) {
		return labelFacade.update(theLabel);
	}
	
	@DeleteMapping("/{labelId}")
	public ResponseEntity<String> deleteLabel(@PathVariable UUID labelId) {
		labelFacade.deleteById(labelId);
		
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
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
