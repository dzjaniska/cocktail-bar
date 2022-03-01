package com.scnsoft.cocktails.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.scnsoft.cocktails.entity.CocktailIngredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CocktailIngredientDTO {
	private UUID id;
	
	private CocktailDTO cocktailDTO;
	
	private IngredientDTO ingredientDTO;
	
	private BigDecimal quantity;
	
	public CocktailIngredientDTO(CocktailIngredient cocktailIngredient, boolean nullCocktail) {
		
		id = cocktailIngredient.getId();
		cocktailDTO = nullCocktail ? null : new CocktailDTO(cocktailIngredient.getCocktail(), true);
		ingredientDTO = nullCocktail ? new IngredientDTO(cocktailIngredient.getIngredient(), true) : null;
		quantity = cocktailIngredient.getQuantity();
	}
}