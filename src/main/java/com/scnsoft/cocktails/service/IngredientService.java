package com.scnsoft.cocktails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.IngredientRepository;
import com.scnsoft.cocktails.dao.LabelRepository;
import com.scnsoft.cocktails.entity.Ingredient;
import com.scnsoft.cocktails.entity.Label;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;

	public Ingredient saveOrUpdate(Ingredient ingredient) {
		if (ingredient.getId() == null)
			return save(ingredient);
		else
			return update(ingredient);
	}

	private Ingredient save(Ingredient ingredient) {
		ingredient.setId(null);
		
		return ingredientRepository.save(ingredient);
	}
	
	public Ingredient update(Ingredient ingredient) {
		ingredientRepository.getById(ingredient.getId());
		
		return ingredientRepository.save(ingredient);
	}
}
