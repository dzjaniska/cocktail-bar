package com.scnsoft.cocktails.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

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
	
	@ManyToOne(fetch = FetchType.EAGER)
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
