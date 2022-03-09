package com.scnsoft.cocktails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.CocktailIngredientRepository;
import com.scnsoft.cocktails.dao.IngredientRepository;
import com.scnsoft.cocktails.dto.IngredientSearch;
import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.CocktailIngredient;
import com.scnsoft.cocktails.entity.Ingredient;
import com.scnsoft.cocktails.entity.Label;

import static org.springframework.data.jpa.domain.Specification.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.scnsoft.cocktails.dao.IngredientSpecifications.*;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private CocktailIngredientRepository cocktailIngredientRepository;
	
	@Autowired
	private LabelService labelService;
	
	@Autowired
	private CocktailService cocktailService;

	public Ingredient saveOrUpdate(Ingredient ingredient, boolean nullCollection) {
		if (ingredient.getId() == null)
			return save(ingredient, nullCollection);
		else
			return update(ingredient, nullCollection);
	}

	public Ingredient save(Ingredient ingredient, boolean nullCollection) {
		return ingredientRepository.save(ingredient);
	}
	
	public Ingredient update(Ingredient ingredient, boolean nullCollection) {
		return ingredientRepository.save(ingredient);
	}

	public Page<Ingredient> findAll(IngredientSearch search, Pageable pageable) {
		
		Specification<Ingredient> specification = empty();
		if (search.getName() != null)
			specification = specification.and(ingredientNameStartsWith(search));
		if (search.getDescription() != null)
			specification = specification.and(ingredientDescriptionContains(search));
		if (search.getAlc() != null)
			specification = specification.and(ingredientAlcEquals(search));

		return ingredientRepository.findAll(specification, pageable);
	}

	public Ingredient findById(UUID ingredientId) {
		return ingredientRepository.getById(ingredientId);
	}

	public void deleteById(UUID ingredientId) {
		Ingredient ingredient = ingredientRepository.getById(ingredientId);
		
		ingredientRepository.deleteById(ingredientId);
	}
}
