package com.scnsoft.cocktails.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CocktailIngredientDTO {
	private UUID id;
	
	private CocktailDTO cocktailDTO;
	
	private IngredientDTO ingredientDTO;
	
	private BigDecimal quantity;
	
	public CocktailIngredientDTO(CocktailIngredient cocktailIngredient) {
		id = cocktailIngredient.getId();
		this.cocktailDTO = null;
		ingredientDTO = new IngredientDTO(cocktailIngredient.getIngredient());
		quantity = cocktailIngredient.getQuantity();
	}
}
