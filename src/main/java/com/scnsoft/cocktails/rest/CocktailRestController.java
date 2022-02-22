package com.scnsoft.cocktails.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scnsoft.cocktails.entity.CocktailDTO;
import com.scnsoft.cocktails.service.CocktailFacade;

@RestController
@RequestMapping("/api/cocktails")
public class CocktailRestController {
	
	@Autowired
	private CocktailFacade cocktailFacade;
	
	@GetMapping
	public List<CocktailDTO> getCocktails(@RequestParam String lang, 
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String ingredientName,
			@RequestParam(required = false) String ingredientDescription) {
		
		lang = lang.toLowerCase();
		List<String> langs = Arrays.asList("en", "ru");
		if (!langs.contains(lang))
			throw new InvalidLangException(lang);
		lang = lang.substring(0, 1).toUpperCase() + lang.substring(1).toLowerCase();
		
		return cocktailFacade.findByNameAndDescriptionAndIngredientNameAndIngredientDescription(lang, name, description, ingredientName, ingredientDescription);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(InvalidLangException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
