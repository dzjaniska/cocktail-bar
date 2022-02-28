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

import com.scnsoft.cocktails.dto.TagDTO;
import com.scnsoft.cocktails.facade.TagFacade;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {
	
	@Autowired
	private TagFacade tagFacade;
	
	@GetMapping
	public List<TagDTO> getTags() {
		return tagFacade.findAll();
	}
	
	@GetMapping("/{tagId}")
	public TagDTO getTag(@PathVariable UUID tagId) {
		return tagFacade.getById(tagId);
	}
	
	@PostMapping
	public TagDTO addTag(@RequestBody TagDTO theTag) {
		return tagFacade.save(theTag);
	}
	
	@PutMapping
	public TagDTO updateTag(@RequestBody TagDTO theTag) {
		return tagFacade.update(theTag);
	}
	
	@DeleteMapping("/{tagId}")
	public ResponseEntity<String> deleteTag(@PathVariable UUID tagId) {
		tagFacade.deleteById(tagId);
		
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
