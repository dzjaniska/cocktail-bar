package com.scnsoft.cocktails.rest;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scnsoft.cocktails.entity.TagDTO;
import com.scnsoft.cocktails.facade.TagFacade;
import com.scnsoft.cocktails.service.LabelService;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {
	
	@Autowired
	private TagFacade tagFacade;
	
	@Autowired
	private LabelService labelService;
	
	@GetMapping
	public List<TagDTO> getTags() {
		return tagFacade.findAll();
	}
	
	@GetMapping("/{tagId}")
	public TagDTO getTag(@PathVariable UUID tagId) {
		return tagFacade.getById(tagId);
	}
	
	@Transactional
	@PostMapping
	public TagDTO addTag(@RequestBody TagDTO theTag) {
		theTag.setId(null);
		
		UUID labelUuid = theTag.getLabel().getId();
		if (labelService.existsById(labelUuid))
		{
			labelService.update(theTag.getLabel());
			theTag.setLabel(labelService.getById(labelUuid));
		}
		else
			theTag.getLabel().setId(null);
		
		return tagFacade.save(theTag);
	}
	
	@PutMapping
	public TagDTO updateTag(@RequestBody TagDTO theTag) {
		tagFacade.getById(theTag.getId());
		
		tagFacade.update(theTag);
		return theTag;
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
