package com.scnsoft.cocktails.rest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.scnsoft.cocktails.dto.CocktailDTO;
import com.scnsoft.cocktails.dto.CocktailSearch;
import com.scnsoft.cocktails.facade.CocktailFacade;

@RestController
@RequestMapping("/api/cocktails")
public class CocktailRestController {
	
	@Autowired
	private CocktailFacade cocktailFacade;
	
	@GetMapping
	public Page<CocktailDTO> getCocktails(CocktailSearch search, Pageable pageable) {
		
		String lang = search.getLang();
		lang = lang.toLowerCase();
		List<String> langs = Arrays.asList("en", "ru");
		if (!langs.contains(lang))
			throw new InvalidLangException(lang);
		lang = lang.substring(0, 1).toUpperCase() + lang.substring(1).toLowerCase();
		search.setLang(lang);
		
		return cocktailFacade.findAll(search, pageable);
	}
	
	@GetMapping("/{cocktailId}")
	public CocktailDTO getCocktail(@PathVariable UUID cocktailId) {
		return cocktailFacade.findById(cocktailId);
	}
	
	@PostMapping
	public CocktailDTO addCocktail(@RequestBody CocktailDTO theCocktail) {
		return cocktailFacade.save(theCocktail);
	}
	
	@PutMapping
	public CocktailDTO updateCocktail(@RequestBody CocktailDTO theCocktail) {
		return cocktailFacade.update(theCocktail);
	}
	
	@DeleteMapping("/{cocktailId}")
	public ResponseEntity<String> deleteCocktail(@PathVariable UUID cocktailId) {
		cocktailFacade.deleteById(cocktailId);
		
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(InvalidLangException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
