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
		ingredient.setId(null);
		
		Label name = ingredient.getLabelName();
		ingredient.setLabelName(labelService.saveOrUpdate(name));
		Label description = ingredient.getLabelDescription();
		ingredient.setLabelDescription(labelService.saveOrUpdate(description));
		
		Ingredient sIngredient = ingredientRepository.save(ingredient);
		
		if (!nullCollection) {
			sIngredient.getIngredientCocktails().stream().forEach(ic -> {
				Cocktail c = ic.getCocktail();
				c.setLabelName(labelService.saveOrUpdate(c.getLabelName()));
				c.setLabelDescription(labelService.saveOrUpdate(c.getLabelDescription()));
				Cocktail cRet = cocktailService.saveOrUpdate(c, true);
				ic.setCocktail(cRet);
			});
			List<CocktailIngredient> list = new ArrayList<>();
			sIngredient.getIngredientCocktails().stream().forEach(ic -> {
				ic.setIngredient(sIngredient);
				CocktailIngredient icRet = cocktailIngredientRepository.save(ic);
				list.add(icRet);
			});
			sIngredient.setIngredientCocktails(list);
		}
		
		return sIngredient;
	}
	
	public Ingredient update(Ingredient ingredient, boolean nullCollection) {
		ingredientRepository.getById(ingredient.getId());
		
		Label name = ingredient.getLabelName();
		ingredient.setLabelName(labelService.saveOrUpdate(name));
		Label description = ingredient.getLabelDescription();
		ingredient.setLabelDescription(labelService.saveOrUpdate(description));
		
		Ingredient sIngredient = ingredientRepository.save(ingredient);
		
		if (!nullCollection) {
			ingredient.getIngredientCocktails().stream().forEach(ic -> {
				Cocktail c = ic.getCocktail();
				c.setLabelName(labelService.saveOrUpdate(c.getLabelName()));
				c.setLabelDescription(labelService.saveOrUpdate(c.getLabelDescription()));
				Cocktail cRet = cocktailService.saveOrUpdate(c, true);
				ic.setCocktail(cRet);
			});
			List<CocktailIngredient> list = new ArrayList<>();
			ingredient.getIngredientCocktails().stream().forEach(ic -> {
				ic.setIngredient(ingredient);
				CocktailIngredient icRet = cocktailIngredientRepository.save(ic);
				list.add(icRet);
			});
			sIngredient.setIngredientCocktails(list);
		}
		
		return sIngredient;
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
		
		cocktailIngredientRepository.deleteAll(ingredient.getIngredientCocktails());
		
		ingredientRepository.deleteById(ingredientId);
	}
}
