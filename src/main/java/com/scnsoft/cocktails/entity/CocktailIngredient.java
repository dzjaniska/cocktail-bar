package com.scnsoft.cocktails.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import com.scnsoft.cocktails.dto.CocktailIngredientDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@NoArgsConstructor
@Getter 
@Setter
@FieldNameConstants
public class CocktailIngredient extends AbstractEntity {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "cocktail_id")
	private Cocktail cocktail;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
	
	private BigDecimal quantity;

	public CocktailIngredient(CocktailIngredientDTO ci, boolean nullCocktail) {
		id = ci.getId();
		cocktail = nullCocktail ? null : new Cocktail(ci.getCocktailDTO(), true);
		ingredient = nullCocktail ? new Ingredient(ci.getIngredientDTO(), true) : null;
		quantity = ci.getQuantity();
	}
}
