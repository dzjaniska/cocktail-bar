package com.scnsoft.cocktails.rest;

import static com.scnsoft.cocktails.rest.RestControllerUtil.processLang;

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
import com.scnsoft.cocktails.dto.IngredientDTO;
import com.scnsoft.cocktails.dto.IngredientSearch;
import com.scnsoft.cocktails.facade.IngredientFacade;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientRestController {
	
	@Autowired
	private IngredientFacade ingredientFacade;
	
	@GetMapping
	public Page<IngredientDTO> getIngredient(IngredientSearch search, Pageable pageable) {
		
		String lang = search.getLang();
		lang = processLang(lang);
		search.setLang(lang);
		
		return ingredientFacade.findAll(search, pageable);
	}
	
	@GetMapping("/{ingredientId}")
	public IngredientDTO getIngredient(@PathVariable UUID ingredientId) {
		return ingredientFacade.findById(ingredientId);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(InvalidLangException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
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
