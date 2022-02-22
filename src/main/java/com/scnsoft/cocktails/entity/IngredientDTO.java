package com.scnsoft.cocktails.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class IngredientDTO {
	private UUID id;
	
	private int alc;
	
	private String unit;
	
	private LabelDTO labelDTOName;
	
	private LabelDTO labelDTODescription;
	
	private List<CocktailIngredient> ingredientCocktailsDTO;
	
	public IngredientDTO(Ingredient ingredient) {
		id = ingredient.getId();
		alc = ingredient.getAlc();
		unit = ingredient.getUnit();
		labelDTOName = new LabelDTO(ingredient.getLabelName());
		labelDTODescription = new LabelDTO(ingredient.getLabelDescription());
		ingredientCocktailsDTO = null;
	}
}
