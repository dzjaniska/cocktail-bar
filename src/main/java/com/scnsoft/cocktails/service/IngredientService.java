package com.scnsoft.cocktails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.IngredientRepository;
import com.scnsoft.cocktails.dto.IngredientSearch;
import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.Ingredient;

import static org.springframework.data.jpa.domain.Specification.*;

import java.util.UUID;

import static com.scnsoft.cocktails.dao.IngredientSpecifications.*;

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
}
