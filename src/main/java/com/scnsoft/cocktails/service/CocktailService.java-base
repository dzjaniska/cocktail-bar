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

	public Cocktail save(Cocktail cocktail) {
		cocktail.setId(null);
		
		Label name = cocktail.getLabelName();
		cocktail.setLabelName(labelService.saveOrUpdate(name));
		Label description = cocktail.getLabelDescription();
		cocktail.setLabelDescription(labelService.saveOrUpdate(description));
		
		Cocktail sCocktail = cocktailRepository.save(cocktail);
		
		sCocktail.getCocktailIngredients().stream().forEach(ci -> { Ingredient i = ci.getIngredient();
																	i.setLabelName(labelService.saveOrUpdate(i.getLabelName()));
																	i.setLabelDescription(labelService.saveOrUpdate(i.getLabelDescription()));
																	Ingredient iRet = ingredientService.saveOrUpdate(i);
																	ci.setIngredient(iRet);});
		List<CocktailIngredient> list = new ArrayList<>();
		sCocktail.getCocktailIngredients().stream().forEach(ci -> { ci.setCocktail(sCocktail);
																	CocktailIngredient ciRet = cocktailIngredientRepository.save(ci); 
																	list.add(ciRet);});
		sCocktail.setCocktailIngredients(list);
		
		return sCocktail;
	}
	
	public Cocktail update(Cocktail cocktail) {
		cocktailRepository.getById(cocktail.getId());
		
		Label name = cocktail.getLabelName();
		cocktail.setLabelName(labelService.saveOrUpdate(name));
		Label description = cocktail.getLabelDescription();
		cocktail.setLabelDescription(labelService.saveOrUpdate(description));
		
		Cocktail sCocktail = cocktailRepository.save(cocktail);
		
		cocktail.getCocktailIngredients().stream().forEach(ci -> { Ingredient i = ci.getIngredient();
																	i.setLabelName(labelService.saveOrUpdate(i.getLabelName()));
																	i.setLabelDescription(labelService.saveOrUpdate(i.getLabelDescription()));
																	Ingredient iRet = ingredientService.saveOrUpdate(i);
																	ci.setIngredient(iRet);});
		List<CocktailIngredient> list = new ArrayList<>();
		cocktail.getCocktailIngredients().stream().forEach(ci -> { ci.setCocktail(cocktail);
																	CocktailIngredient ciRet = cocktailIngredientRepository.save(ci); 
																	list.add(ciRet);});
		sCocktail.setCocktailIngredients(list);
		
		return sCocktail;
	}
	
	public void deleteById(UUID id) {
		Cocktail cocktail = cocktailRepository.getById(id);
		
		cocktailIngredientRepository.deleteAll(cocktail.getCocktailIngredients());
		
		cocktailRepository.deleteById(id);
	}
}
