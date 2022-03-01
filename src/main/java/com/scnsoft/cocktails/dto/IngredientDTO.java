package com.scnsoft.cocktails.dto;

import java.util.List;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.scnsoft.cocktails.entity.CocktailIngredient;
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
	
	private LabelDTO labelDTOName;
	
	private LabelDTO labelDTODescription;
	
	private List<CocktailIngredientDTO> ingredientCocktailsDTO;
	
	public IngredientDTO(Ingredient ingredient, boolean nullCollection) {
		id = ingredient.getId();
		alc = ingredient.getAlc();
		unit = ingredient.getUnit();
		labelDTOName = new LabelDTO(ingredient.getLabelName());
		labelDTODescription = new LabelDTO(ingredient.getLabelDescription());
		ingredientCocktailsDTO = nullCollection ? null : ingredient
														.getIngredientCocktails()
														.stream()
														.map(ic -> new CocktailIngredientDTO(ic, false))
														.toList();
	}
}
