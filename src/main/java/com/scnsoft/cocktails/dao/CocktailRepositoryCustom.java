package com.scnsoft.cocktails.dao;

import java.util.List;

import com.scnsoft.cocktails.dto.CocktailSearch;
import com.scnsoft.cocktails.entity.Cocktail;

public interface CocktailRepositoryCustom {
	List<Cocktail> findByNameAndDescriptionAndIngredientNameAndIngredientDescription(CocktailSearch search);
}
