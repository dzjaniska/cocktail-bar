package com.scnsoft.cocktails.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.CocktailIngredientRepository;
import com.scnsoft.cocktails.dao.CocktailRepository;
import com.scnsoft.cocktails.dto.CocktailSearch;
import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.CocktailIngredient;
import com.scnsoft.cocktails.entity.Ingredient;
import com.scnsoft.cocktails.entity.Label;

import static org.springframework.data.jpa.domain.Specification.*;
import static com.scnsoft.cocktails.dao.CocktailSpecifications.*;

@Service
public class CocktailService {
	
	@Autowired
	CocktailRepository cocktailRepository;
	
	@Autowired
	CocktailIngredientRepository cocktailIngredientRepository;
	
	@Autowired
	LabelService labelService;
	
	@Autowired
	IngredientService ingredientService;
	
	public Page<Cocktail> findAll(CocktailSearch search, Pageable pageable) {
		
		Specification<Cocktail> specification = empty();
		if (search.getName() != null)
			specification = specification.and(cocktailNameStartsWith(search));
		if (search.getDescription() != null)
			specification = specification.and(cocktailDescriptionContains(search));
		if (search.getIngredientName() != null)
			specification = specification.and(cocktailIngredientStartsWith(search));
		if (search.getIngredientDescription() != null)
			specification = specification.and(cocktailIngredientContains(search));

		return cocktailRepository.findAll(specification, pageable);
	}
	
	public Cocktail findById(UUID id) {
		return cocktailRepository.getById(id);
	}

	public Cocktail save(Cocktail cocktail, boolean nullCollection) {
		return cocktailRepository.save(cocktail);
	}
	
	public Cocktail update(Cocktail cocktail, boolean nullCollection) {
		return cocktailRepository.save(cocktail);
	}
	
	public Cocktail saveOrUpdate(Cocktail cocktail, boolean nullCollection) {
		if (cocktail.getId() == null)
			return save(cocktail, nullCollection);
		else
			return update(cocktail, nullCollection);
	}
	
	public void deleteById(UUID id) {
		Cocktail cocktail = cocktailRepository.getById(id);
		
		cocktailRepository.deleteById(id);
	}
}
