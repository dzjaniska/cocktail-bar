package com.scnsoft.cocktails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.entity.CocktailDTO;

@Transactional
@Component
public class CocktailFacade {
	
	@Autowired
	CocktailService cocktailService;
	
	public List<CocktailDTO> findByNameAndDescriptionAndIngredientNameAndIngredientDescription(String lang, String name,
			String description, String ingredientName, String ingredientDescription) {
		cocktailService.findAll();
		return cocktailService
				.findByNameAndDescriptionAndIngredientNameAndIngredientDescription(lang, name, description, ingredientName, ingredientDescription)
				.stream()
				.map(c -> new CocktailDTO(c))
				.toList();
	}
	
	

}
