package com.scnsoft.cocktails.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.CocktailRepository;
import com.scnsoft.cocktails.entity.Cocktail;

@Service
public class CocktailService {
	
	@Autowired
	CocktailRepository cocktailRepository;
	
	public List<Cocktail> findByNameAndDescriptionAndIngredientNameAndIngredientDescription(String lang, String name,
			String description, String ingredientName, String ingredientDescription) {
		return cocktailRepository.findByNameAndDescriptionAndIngredientNameAndIngredientDescription(lang, name, description, ingredientName, ingredientDescription);
	}

	public List<Cocktail> findAll() {
		return cocktailRepository.findAll();
	}
	
	public Cocktail findById(UUID id) {
		return cocktailRepository.getById(id);
	}

}
