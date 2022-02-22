package com.scnsoft.cocktails.dao;

import java.util.List;

import com.scnsoft.cocktails.entity.Cocktail;

public interface CocktailRepositoryCustom {
	List<Cocktail> findByNameAndDescriptionAndIngredientNameAndIngredientDescription(String lang, String name, String description, String ingredientName, String ingredientDescription);
}
