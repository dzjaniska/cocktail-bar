package com.scnsoft.cocktails.dto;

import java.util.List;
import java.util.UUID;

import com.scnsoft.cocktails.entity.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class IngredientDTO {
	private UUID id;
	
	private int alc;
	
	private String unit;
	
	private LabelDTO name;
	
	private LabelDTO description;
	
	private List<CocktailIngredientDTO> ingredientCocktails;
	
	public IngredientDTO(Ingredient ingredient, boolean nullCollection) {
		id = ingredient.getId();
		alc = ingredient.getAlc();
		unit = ingredient.getUnit();
		name = new LabelDTO(ingredient.getName());
		description = new LabelDTO(ingredient.getDescription());
		ingredientCocktails = nullCollection ? null : ingredient
														.getIngredientCocktails()
														.stream()
														.map(ic -> new CocktailIngredientDTO(ic, false))
														.toList();
	}
}
