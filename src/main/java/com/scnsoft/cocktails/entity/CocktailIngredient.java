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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cocktail_id")
	private Cocktail cocktail;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
	
	private BigDecimal quantity;

	public CocktailIngredient(CocktailIngredientDTO ci) {
		id = ci.getId();
		cocktail = null;
		ingredient = new Ingredient(ci.getIngredient());
		quantity = ci.getQuantity();
	}
}
