package com.scnsoft.cocktails.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CocktailDTO {
	private UUID id;
	
	private String image;
	
	private LabelDTO labelDTOName;

	private LabelDTO labelDTODescription;

	private List<CocktailIngredientDTO> cocktailIngredientsDTO;
	
	public CocktailDTO(Cocktail cocktail) {
		id = cocktail.getId();
		image = cocktail.getImage();
		labelDTOName = new LabelDTO(cocktail.getLabelName());
		labelDTODescription = new LabelDTO(cocktail.getLabelDescription());
		cocktailIngredientsDTO = cocktail
				.getCocktailIngredients()
				.stream()
				.map(ci -> new CocktailIngredientDTO(ci))
				.toList();
	}
}
