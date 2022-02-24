package com.scnsoft.cocktails.facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.entity.CocktailDTO;
import com.scnsoft.cocktails.service.CocktailService;

@Transactional
@Component
public class CocktailFacade {
	
	@Autowired
	CocktailService cocktailService;
	
	public List<CocktailDTO> findByNameAndDescriptionAndIngredientNameAndIngredientDescription(String lang, String name,
			String description, String ingredientName, String ingredientDescription) {
		
		return cocktailService
				.findByNameAndDescriptionAndIngredientNameAndIngredientDescription(lang, name, description, ingredientName, ingredientDescription)
				.stream()
				.map(c -> new CocktailDTO(c))
				.toList();
	}

	public List<CocktailDTO> findAll() {
		
		return cocktailService
				.findAll()
				.stream()
				.map(c -> new CocktailDTO(c))
				.toList();
	}
	
	public CocktailDTO findById(UUID id) {
		return new CocktailDTO(cocktailService.findById(id));
	}
}
