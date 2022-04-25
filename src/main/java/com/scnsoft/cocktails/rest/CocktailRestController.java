package com.scnsoft.cocktails.rest;

import static com.scnsoft.cocktails.rest.RestControllerUtil.processLang;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

import com.scnsoft.cocktails.aspect.ProfileTime;
import com.scnsoft.cocktails.dto.CocktailDTO;
import com.scnsoft.cocktails.dto.CocktailSearch;
import com.scnsoft.cocktails.facade.CocktailFacade;

@RestController
@RequestMapping("/api/cocktails")
@ProfileTime
public class CocktailRestController {
	
	@Autowired
	private CocktailFacade cocktailFacade;
	
	@GetMapping
	public Page<CocktailDTO> getCocktails(CocktailSearch search, Pageable pageable) {
		
		String lang = search.getLang();
		lang = processLang(lang);
		search.setLang(lang);
		
		return cocktailFacade.findAll(search, pageable);
	}
	
	@GetMapping("/{cocktailId}")
	@Cacheable("cocktails")
	public CocktailDTO getCocktail(@PathVariable UUID cocktailId) {
		return cocktailFacade.findById(cocktailId);
	}
	
	@PostMapping
	public CocktailDTO addCocktail(@RequestBody CocktailDTO theCocktail) {
		return cocktailFacade.save(theCocktail);
	}
	
	@PutMapping
	@CachePut(value = "cocktails", key = "#theCocktail.id")
	public CocktailDTO updateCocktail(@RequestBody CocktailDTO theCocktail) {
		return cocktailFacade.update(theCocktail);
	}
	
	@DeleteMapping("/{cocktailId}")
	@CacheEvict(value = "cocktails", key = "#cocktailId")
	public ResponseEntity<String> deleteCocktail(@PathVariable UUID cocktailId) {
		cocktailFacade.delete(cocktailId);
		
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
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
